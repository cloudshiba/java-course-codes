package com.cloudshiba.mymq.server.core;

import com.cloudshiba.mymq.server.config.CommonConfig;
import com.cloudshiba.mymq.server.core.impl.MyConsumerGroup;
import com.cloudshiba.mymq.server.core.impl.MyTopic;
import com.cloudshiba.mymq.server.exception.MyMqException;
import com.cloudshoba.mymq.api.vo.MessageResp;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class StringMqRepository {
    @Getter
    private final Map<ConsumerGroupDefinition, IConsumerGroup<String>> consumerGroupMap = Maps.newConcurrentMap();
    private final Map<String, ITopic<String>> topicMap = Maps.newConcurrentMap();

    public ITopic<String> createTopic(String topicName) {
        return topicMap.computeIfAbsent(topicName, name -> MyTopic.create(name, CommonConfig.DEFAULT_TOPIC_CAPACITY));
    }

    public IConsumerGroup<String> createConsumerGroup(String groupName, ITopic<String> topic) {
        ConsumerGroupDefinition consumerGroupDefinition = ConsumerGroupDefinition.builder()
                .groupName(groupName)
                .topicName(topic.getName())
                .build();
        return consumerGroupMap.computeIfAbsent(consumerGroupDefinition, definition -> MyConsumerGroup.create(definition.getGroupName(), topic));
    }

    public boolean offerMessage(String topicName, String message) {
        return Optional.ofNullable(topicMap.get(topicName)).orElse(createTopic(topicName))
                .offer(message);
    }

    public boolean updateOffset(String groupName, String topicName, int offset) {
        ConsumerGroupDefinition consumerGroupDefinition = ConsumerGroupDefinition.builder()
                .groupName(groupName)
                .topicName(topicName)
                .build();
        return Optional.ofNullable(consumerGroupMap.get(consumerGroupDefinition)).orElseThrow(() -> new MyMqException("没有找到指定的消費者组: " + groupName))
                .updateOffset(offset);
    }

    public Optional<MessageResp<String>> pullMessage(String groupName, String topicName) {
        ConsumerGroupDefinition consumerGroupDefinition = ConsumerGroupDefinition.builder()
                .groupName(groupName)
                .topicName(topicName)
                .build();
        return Optional.ofNullable(consumerGroupMap.get(consumerGroupDefinition)).orElseThrow(() -> new MyMqException("没有找到指定的消費者组: " + groupName))
                .pull();
    }
}
