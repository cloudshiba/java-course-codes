package com.cloudshoba.mymq.client.consumer;

import com.cloudshoba.mymq.api.MyMqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = {"com.cloudshoba.mymq"})
@EnableFeignClients(basePackages = {"com.cloudshoba.mymq.api"})
@SpringBootApplication
public class MyMqClientConsumerApplication implements ApplicationRunner {
    @Autowired
    private MyMqClient myMqClient;

    public static void main(String[] args) {
        SpringApplication.run(MyMqClientConsumerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myMqClient.subscribe("spring-group-1", "topic-1", message -> {
            log.info("收到訊息：spring-group-1: " + message);
        });

        myMqClient.subscribe("spring-group-2", "topic-2", message -> {
            log.info("收到訊息：spring-group-2: " + message);
        });
    }
}
