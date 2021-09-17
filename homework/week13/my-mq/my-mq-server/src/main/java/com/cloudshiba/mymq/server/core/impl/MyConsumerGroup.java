package com.cloudshiba.mymq.server.core.impl;

import com.cloudshiba.mymq.server.core.IConsumerGroup;
import com.cloudshiba.mymq.server.core.ITopic;
import com.cloudshoba.mymq.api.vo.MessageResp;
import lombok.Getter;

import java.util.Optional;

public class MyConsumerGroup<T> implements IConsumerGroup<T> {
    @Getter
    private String name;

    private ITopic<T> topic;

    @Getter
    private volatile int offset;

    private MyConsumerGroup(String name, ITopic<T> topic) {
        this.name = name;
        this.topic = topic;
    }

    public static <T> IConsumerGroup<T> create(String name, ITopic<T> topic) {
        return new MyConsumerGroup<>(name, topic);
    }

    @Override
    public ITopic<T> topic() {
        return topic;
    }

    @Override
    public boolean updateOffset(int offset) {
        this.offset = offset;
        return true;
    }

    @Override
    public Optional<MessageResp<T>> pull() {
        int currentOffset = offset;
        if (currentOffset > topic.maxIndex()) {
            return Optional.empty();
        }

        MessageResp<T> msgResp = new MessageResp<>();
        msgResp.setMessage(topic.getByIndex(currentOffset));
        msgResp.setIndex(currentOffset);
        return Optional.of(msgResp);
    }
}
