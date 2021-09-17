package com.cloudshiba.mymq.server.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerGroupDefinition {
    private String groupName;

    private String topicName;
}
