package com.cloudshiba.messagingjms.activemq;

import com.cloudshiba.messagingjms.config.ActiveConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @JmsListener(destination = ActiveConfig.QUEUE_NAME)
    public void receive(String message) {
        System.out.println("消費的 message 是：" + message);
    }

    @JmsListener(destination = ActiveConfig.TOPIC_NAME)
    public void  topicReceive1(String message) {
        System.out.println("消費者 1 接收的 topic 是：" + message);
    }

    @JmsListener(destination = ActiveConfig.TOPIC_NAME)
    public void  topicReceive2(String message) {
        System.out.println("消費者 2 接收的 topic 是：" + message);
    }
}
