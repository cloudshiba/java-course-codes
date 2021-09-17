package com.cloudshiba.mymq.server.core;

public interface ITopic<T> {
    /**
     * Topic 名稱
     */
    String getName();

    /**
     * Topic 容量
     */
    int getCapacity();

    /**
     * 生產訊息
     */
    boolean offer(T message);

    /**
     * 根據索引獲取訊息
     */
    T getByIndex(int index);

    /**
     * 當前最大索引值
     */
    int maxIndex();
}
