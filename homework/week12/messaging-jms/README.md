# ActiveMQ 生產與消費訊息

## 發送訊息到指定的 Queue

### ActiveMQ 設定
```java
@Configuration
public class ActiveConfig {
    public static final String QUEUE_NAME = "my.queue";

    // 建立一個隊列
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }
}
```

### Sender 設定
```java
@Component
public class Sender {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue queue;

    public Sender(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
    }

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
```

### 模擬發送 100 筆訊息
```java
    @PostConstruct
    public void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("生產訊息 " + i);
            sender.send("Hello, this is message count " + i);
        }
        stopWatch.stop();
        System.out.println("發送消息耗時：" + stopWatch.getTotalTimeMillis());
    }
```

## 消費 Queue 的訊息

### Receiver 設定
```java
@Component
public class Receiver {
    @JmsListener(destination = ActiveConfig.QUEUE_NAME)
    public void receive(String message) {
        System.out.println("消費的 message 是：" + message);
    }
}
```
