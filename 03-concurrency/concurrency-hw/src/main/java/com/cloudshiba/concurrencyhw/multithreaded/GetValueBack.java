package com.cloudshiba.concurrencyhw.multithreaded;

import com.cloudshiba.concurrencyhw.multithreaded.asynchronous.CompletableFutureHandler;
import com.cloudshiba.concurrencyhw.multithreaded.threadpool.FutureTaskHandler;
import com.cloudshiba.concurrencyhw.multithreaded.threadpool.ScheduledThreadPoolHandler;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class GetValueBack {
    private static final int CORE_POOL_SIZE = 5;
    private static final int SCHEDULE_DELAY_MILLISECONDS = 500;
    private static final int FUTURE_DELAY_MILLISECONDS = 900;

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 使用 ScheduledThreadPool 機制
        int scheduledThreadPoolResult = ScheduledThreadPoolHandler.getResult(CORE_POOL_SIZE, new SumTask(), SCHEDULE_DELAY_MILLISECONDS, FUTURE_DELAY_MILLISECONDS);
        // 使用 CompletableFuture 機制
        int completeFutureResult = CompletableFutureHandler.getResult(sum());
        // 使用 FutureTask
        int futureTaskResult = FutureTaskHandler.getResult(CORE_POOL_SIZE, new SumTask(), FUTURE_DELAY_MILLISECONDS);

        // 确保  拿到result 并输出
        System.out.println("ScheduledThreadPoolHandler Result: " + scheduledThreadPoolResult);
        System.out.println("CompletableFutureHandler Result: " + completeFutureResult);
        System.out.println("FutureTaskHandler Result: " + futureTaskResult);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

        Thread.sleep(FUTURE_DELAY_MILLISECONDS);
        System.out.println("結束 main 方法");
    }

    private static class SumTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return sum();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
