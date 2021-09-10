package com.cloudshiba.messagingjms.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveConfig {
    public static final String QUEUE_NAME = "my.queue";
    public static final String TOPIC_NAME = "my.topic";

    // 建立一個隊列
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }

    // 建立一個主題
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(TOPIC_NAME);
    }
}
