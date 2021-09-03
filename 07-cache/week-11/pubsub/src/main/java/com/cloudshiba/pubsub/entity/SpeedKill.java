package com.cloudshiba.pubsub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data @NoArgsConstructor @AllArgsConstructor
public class SpeedKill {
    private long id;

    private String name;

    private int number;

    private Instant startTime;

    private Integer endTime;

    private Instant createTime;
}
