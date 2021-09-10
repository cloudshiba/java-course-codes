package com.cloudshiba.messagingjms;

import com.cloudshiba.messagingjms.activemq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    @Autowired
    private Sender sender;

    @PostConstruct
    public void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("生產訊息 " + i);
            sender.send("Hello, this is message count " + i);
            sender.sendTopic("send topic " + i);
        }
        stopWatch.stop();
        System.out.println("發送消息耗時：" + stopWatch.getTotalTimeMillis());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
