package com.cloudshiba.concurrencyhw.multithreaded.asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureHandler {
    public static int getResult(int input) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture
                = CompletableFuture.supplyAsync(() -> input);
        return completableFuture.get();
    }
}
