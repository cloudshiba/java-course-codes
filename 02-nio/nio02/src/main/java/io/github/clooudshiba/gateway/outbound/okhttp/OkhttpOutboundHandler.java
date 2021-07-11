package io.github.clooudshiba.gateway.outbound.okhttp;

import io.github.clooudshiba.gateway.filter.HeaderHttpRequestFilter;
import io.github.clooudshiba.gateway.filter.HeaderHttpResponseFilter;
import io.github.clooudshiba.gateway.filter.HttpRequestFilter;
import io.github.clooudshiba.gateway.filter.HttpResponseFilter;
import io.github.clooudshiba.gateway.outbound.utils.UrlUtils;
import io.github.clooudshiba.gateway.router.HttpEndpointRouter;
import io.github.clooudshiba.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import okhttp3.*;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler {
    private static final Logger logger = LoggerFactory.getLogger(OkhttpOutboundHandler.class);
    private static final int connectTimeoutSecond = 1;
    private static final int readTimeoutSecond = 3;

    private List<String> backendUrls;
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(connectTimeoutSecond, TimeUnit.SECONDS)
            .writeTimeout(connectTimeoutSecond, TimeUnit.SECONDS)
            .readTimeout(readTimeoutSecond, TimeUnit.SECONDS)
            .build();

    HttpEndpointRouter router = new RandomHttpEndpointRouter();
    HttpResponseFilter responseFilter = new HeaderHttpResponseFilter();

    public OkhttpOutboundHandler(List<String> backends) {
        this.backendUrls = UrlUtils.getUrls(backends);
        logger.info("backendUrls: {}", backendUrls);
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        fetchGet(fullRequest, ctx, url);
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        String headerValue = inbound.headers().get("Shiba-Request");
        logger.info("custom header: {}", headerValue);

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "OkHttp")
                .addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE)
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Shiba-Request", headerValue)
                .build();
        final Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                call.cancel();
                logger.error("OKHttp failure {}", call);
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                logger.info("Call {}", call.request());
                try(ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    try {
                        handleResponse(inbound, ctx, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        response.close();
                    }
                }
            }
        });
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse) throws Exception {
        FullHttpResponse response = null;

        try {
            String contentLength = endpointResponse.header("Content-Length");
            logger.info("contentLength: {}", contentLength);

            byte[] body = endpointResponse.body().bytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().setInt("Content-Length", Integer.parseInt(contentLength));
            responseFilter.filter(response);
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            ctx.close();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
