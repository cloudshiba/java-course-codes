package com.cloudshiba.mymq.server.service;

import com.cloudshoba.mymq.api.vo.MessageResp;
import com.cloudshoba.mymq.api.vo.MqOverviewVo;

public interface IMqBrokerService {
    MqOverviewVo prettyOverview();

    void createConsumerGroup(String groupName, String topicName);

    boolean offerMessage(String topicName, String message);

    MessageResp<String> pullMessage(String groupName, String topicName);

    MessageResp<String> simplePullMessage(String groupName, String topicName);

    boolean updateOffset(String groupName, String topicName, int offset);
}
