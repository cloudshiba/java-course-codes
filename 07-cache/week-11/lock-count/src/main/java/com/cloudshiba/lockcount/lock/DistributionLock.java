package com.cloudshiba.lockcount.lock;

public interface DistributionLock {
    /**
     * 獲取分布式鎖，如果鎖已被其他 process 取得，則每隔 10 ms 繼續獲取，直到獲取成功
     */
    void acquire();

    /**
     * 嘗試獲取分布式鎖，獲取成功回傳 true，如果已被其他 process 取得則回傳 false
     */
    boolean tryAcquire();

    /**
     * 釋放鎖，如果對應的鎖存在則釋放成功回傳 true，如果不存在或鎖比對不符則釋放失敗回傳 false
     */
    boolean release();
}
