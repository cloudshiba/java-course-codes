package com.cloudshiba.mymq.server.core;

import com.cloudshoba.mymq.api.vo.MessageResp;

import java.util.Optional;

public interface IConsumerGroup<T> {
    /**
     * 消費者群組名稱
     */
    String getName();

    /**
     * 消費偏移量
     */
    int getOffset();

    /**
     * 關聯 Topic
     */
    ITopic<T> topic();

    /**
     * 更新偏移量
     */
    boolean updateOffset(int offset);

    /**
     * 消費訊息
     */
    Optional<MessageResp<T>> pull();
}
