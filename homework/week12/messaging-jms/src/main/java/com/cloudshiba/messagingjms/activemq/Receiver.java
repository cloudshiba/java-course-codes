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
}
