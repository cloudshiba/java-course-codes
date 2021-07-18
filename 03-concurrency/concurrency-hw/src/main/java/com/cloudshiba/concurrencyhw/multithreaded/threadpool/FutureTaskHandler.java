package com.cloudshiba.concurrencyhw.multithreaded.threadpool;

import com.cloudshiba.concurrencyhw.multithreaded.utils.CloseThreadPool;

import java.util.concurrent.*;

public class FutureTaskHandler {
    public static int getResult(int corePoolSize, Callable<Integer> callable, int futureDelayMilliSeconds)
            throws ExecutionException, InterruptedException, TimeoutException
    {
        ExecutorService threadPool = getExecutorService(corePoolSize);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        threadPool.submit(futureTask);
        CloseThreadPool.shudown(threadPool);
        return futureTask.get(futureDelayMilliSeconds, TimeUnit.MILLISECONDS);
    }

    private static ExecutorService getExecutorService(int corePoolSize) {
        return Executors.newFixedThreadPool(corePoolSize);
    }
}
