package com.cloudshiba.lockcount.lock;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DistributionLockFactory {
    private DistributionLockFactory(){}

    public static DistributionLock getLock(String key, int expireTime) {
        RedisURI uri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient client = RedisClient.create(uri);
        StatefulRedisConnection<String,String> connection = client.connect();
        return new LettuceDistributionLock(connection,key, expireTime);
    }

}
