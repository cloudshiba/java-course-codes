package com.cloudshoba.mymq.client.producer;

import com.cloudshoba.mymq.api.MyMqClient;
import com.cloudshoba.mymq.api.vo.MessageOfferReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@RestController
@RequestMapping("/mq/producer")
@ComponentScan(basePackages = {"com.cloudshoba.mymq"})
@EnableFeignClients(basePackages = {"com.cloudshoba.mymq.api"})
@SpringBootApplication
public class MyMqClientProducerApplication implements ApplicationRunner {
    @Autowired
    private MyMqClient myMqClient;

    public static void main(String[] args) {
        SpringApplication.run(MyMqClientProducerApplication.class, args);
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody MessageOfferReq req) {
        myMqClient.sendMessage(req.getTopicName(), req.getMessage());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        LongAdder adder = new LongAdder();
        Runnable topic1 = () -> myMqClient.sendMessage("topic-1", "topic-1-msg:" + adder.toString());
        Runnable topic2 = () -> myMqClient.sendMessage("topic-2", "topic-2-msg:" + adder.toString());

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            adder.increment();
            if (random.nextBoolean()) {
                topic1.run();
            } else {
                topic2.run();
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
