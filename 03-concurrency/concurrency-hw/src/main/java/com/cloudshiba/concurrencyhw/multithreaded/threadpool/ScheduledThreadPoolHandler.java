package com.cloudshiba.concurrencyhw.multithreaded.threadpool;

import com.cloudshiba.concurrencyhw.multithreaded.utils.CloseThreadPool;

import java.util.concurrent.*;

public class ScheduledThreadPoolHandler {
    public static int getResult(int corePoolSize, Callable<Integer> callable,
                                int scheduledDelayMilliSeconds, int futureDelayMilliSeconds)
            throws ExecutionException, InterruptedException, TimeoutException
    {
        ScheduledExecutorService threadPool = getExecutorService(corePoolSize);
        Future<Integer> future = threadPool.schedule(() -> {
            return callable.call();
        }, scheduledDelayMilliSeconds, TimeUnit.MILLISECONDS);
        CloseThreadPool.shudown(threadPool);
        return future.get(futureDelayMilliSeconds, TimeUnit.MILLISECONDS);
    }

    private static ScheduledExecutorService getExecutorService(int corePoolSize) {
        return Executors.newScheduledThreadPool(corePoolSize);
    }
}
