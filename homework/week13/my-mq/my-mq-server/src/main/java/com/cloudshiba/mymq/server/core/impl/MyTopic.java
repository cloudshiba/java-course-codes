package com.cloudshiba.mymq.server.core.impl;

import com.cloudshiba.mymq.server.core.ITopic;
import com.cloudshiba.mymq.server.exception.MyMqException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MyTopic<T> implements ITopic<T> {
    @Getter
    private final String name;

    @Getter
    private final int capacity;

    private final List<T> queue;

    private MyTopic(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.queue = new ArrayList<>(capacity);
    }

    public static <T> MyTopic<T> create(String name, int capacity) {
        return new MyTopic<>(name, capacity);
    }

    @Override
    public boolean offer(T message) {
        if (maxIndex() + 1 >= capacity) {
            return false;
        }
        queue.add(message);
        return true;
    }

    @Override
    public T getByIndex(int index) {
        if (index >= queue.size()) {
            throw new MyMqException("topic越界:" + name + ", index: " + index);
        }
        return queue.get(index);
    }

    @Override
    public int maxIndex() {
        return queue.size() - 1;
    }
}
