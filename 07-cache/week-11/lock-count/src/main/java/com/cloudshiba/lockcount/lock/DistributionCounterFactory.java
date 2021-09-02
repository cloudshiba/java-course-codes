package com.cloudshiba.lockcount.lock;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DistributionCounterFactory {

    private DistributionCounterFactory(){}

    public static DistributionCounter getCounter(String key) {
        return getCounter(key, 0L);
    }

    public static DistributionCounter getCounter(String key, long initCount) {
        RedisURI uri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient client = RedisClient.create(uri);
        StatefulRedisConnection<String,String> connection = client.connect();
        return new LettuceDistributionCounter(connection, key, initCount);
    }
}
