package io.github.clooudshiba.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        System.out.println("fullRequest: " + fullRequest);
        fullRequest.headers().set("Shiba-Request", "Hi");
    }
}
