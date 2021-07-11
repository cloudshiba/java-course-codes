package io.github.clooudshiba.gateway.outbound.utils;

import java.util.List;
import java.util.stream.Collectors;

public class UrlUtils {
    public static List<String> getUrls(List<String> backends) {
        return backends.stream().map(UrlUtils::formatUrl).collect(Collectors.toList());
    }

    private static String formatUrl(String backend) {
        return backend.endsWith("/") ? backend.substring(0,backend.length()-1) : backend;
    }
}
