package com.cloudshiba.messagingjms.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveConfig {
    public static final String QUEUE_NAME = "my.queue";

    // 建立一個隊列
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }
}
