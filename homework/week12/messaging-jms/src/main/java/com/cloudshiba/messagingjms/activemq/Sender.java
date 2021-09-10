package com.cloudshiba.messagingjms.activemq;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class Sender {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue queue;
    private final Topic topic;

    public Sender(JmsMessagingTemplate jmsMessagingTemplate, Queue queue, Topic topic) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
        this.topic = topic;
    }

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    public void sendTopic(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }
}
