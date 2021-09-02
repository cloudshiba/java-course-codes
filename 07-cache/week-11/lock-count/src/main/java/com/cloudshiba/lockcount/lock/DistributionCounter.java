package com.cloudshiba.lockcount.lock;

public interface DistributionCounter {
    /**
     * 設定計數器計數值，如果數值為負數或設定失敗，則回傳 false，設定成功回傳 true
     */
    boolean setCounter(long count);

    /**
     * 獲取計數值
     */
    Long get();

    /**
     * 計數加一，增加成功則回傳計數值，否則回傳 null
     */
    Long increase();

    /**
     * 計數減一，如果計數器相減後變成負數或設定失敗則回傳 null，如果設定成功回傳運算後計數
     */
    Long decrease();

    /**
     * 計數器增加指定值，如果成功則回傳 true，否則環傳 false
     */
    Long increase(long change);

    /**
     * 計數器減少指定值，如果計數器相減之後變為負數或設定失敗則回傳 false，成功回傳 true
     */
    Long decrease(long change);
}
