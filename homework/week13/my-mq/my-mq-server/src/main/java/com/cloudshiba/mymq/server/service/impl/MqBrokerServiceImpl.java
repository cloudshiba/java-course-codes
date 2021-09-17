package com.cloudshiba.mymq.server.service.impl;

import com.cloudshiba.mymq.server.core.ITopic;
import com.cloudshiba.mymq.server.core.StringMqRepository;
import com.cloudshiba.mymq.server.service.IMqBrokerService;
import com.cloudshoba.mymq.api.vo.MessageResp;
import com.cloudshoba.mymq.api.vo.MqOverviewVo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MqBrokerServiceImpl implements IMqBrokerService {
    private final StringMqRepository stringMqRepository;

    public MqBrokerServiceImpl(StringMqRepository stringMqRepository) {
        this.stringMqRepository = stringMqRepository;
    }

    @Override
    @SneakyThrows
    public MqOverviewVo prettyOverview() {
        return MqOverviewVo.builder().groupList(stringMqRepository.getConsumerGroupMap().values().stream()
                        .map(group -> {
                            ITopic topic = group.topic();
                            return MqOverviewVo.ConsumerGroup.builder()
                                    .groupName(group.getName())
                                    .offset(group.getOffset())
                                    .topic(MqOverviewVo.Topic.builder()
                                            .name(topic.getName())
                                            .maxIndex(topic.maxIndex())
                                            .capacity(topic.getCapacity())
                                            .build())
                                    .build();
                        }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public void createConsumerGroup(String groupName, String topicName) {
        ITopic<String> topic = stringMqRepository.createTopic(topicName);
        stringMqRepository.createConsumerGroup(groupName, topic);
    }

    @Override
    public boolean offerMessage(String topicName, String message) {
        return stringMqRepository.offerMessage(topicName, message);
    }

    @Override
    public MessageResp<String> simplePullMessage(String groupName, String topicName) {
        MessageResp<String> messageResp = pullMessage(groupName, topicName);
        if (messageResp.getPullSuccess()) {
            updateOffset(groupName, topicName, messageResp.getIndex() + 1);
        }
        return messageResp;
    }

    @Override
    public MessageResp<String> pullMessage(String groupName, String topicName) {
        return stringMqRepository.pullMessage(groupName, topicName)
                .map(item -> {
                    item.setPullSuccess(Boolean.TRUE);
                    return item;
                })
                .orElse(MessageResp.<String>builder()
                        .pullSuccess(Boolean.FALSE)
                        .build());
    }

    @Override
    public boolean updateOffset(String groupName, String topicName, int offset) {
        return stringMqRepository.updateOffset(groupName, topicName, offset);
    }
}
