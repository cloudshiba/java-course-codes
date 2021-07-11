package io.github.clooudshiba.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        System.out.println("response " + response);
        response.headers().set("Content-Type", "application/json");
        response.headers().set("Shiba-Response", "OK");
    }
}
