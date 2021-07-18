package com.cloudshiba.concurrencyhw.multithreaded.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class CloseThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(CloseThreadPool.class);
    private static final int AWAIT_MILLISECONDS = 1000;

    public static void shudown(ExecutorService executorService) {
        logger.info("關閉 {}", executorService.getClass().getName());
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(AWAIT_MILLISECONDS, TimeUnit.MILLISECONDS)) {
                logger.info("強制關閉 {}", executorService.getClass().getName());
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            logger.info("因為異常而強制關閉 {}", executorService.getClass().getName());
            executorService.shutdownNow();
        }
    }
}
