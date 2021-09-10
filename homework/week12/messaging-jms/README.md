# ActiveMQ 生產與消費訊息

## 發送訊息到指定的 Queue

### ActiveMQ 設定
```java
@Configuration
public class ActiveConfig {
    public static final String QUEUE_NAME = "my.queue";
    public static final String TOPIC_NAME = "my.topic";

    // 建立一個隊列
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }

    // 建立一個主題
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(TOPIC_NAME);
    }
}
```

### Sender 設定
```java
@Component
public class Sender {
    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue queue;
    private final Topic topic;

    public Sender(JmsMessagingTemplate jmsMessagingTemplate, Queue queue, Topic topic) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
        this.topic = topic;
    }

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    public void sendTopic(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
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
            sender.sendTopic("send topic " + i);
        }
        stopWatch.stop();
        System.out.println("發送消息耗時：" + stopWatch.getTotalTimeMillis());
    }
```

### 消費 Queue 的訊息
```yaml
  jms:
    pub-sub-domain: false  # 設定 false 即開啟 Queue 模式
```

```java
@Component
public class Receiver {
    @JmsListener(destination = ActiveConfig.QUEUE_NAME)
    public void receive(String message) {
        System.out.println("消費的 message 是：" + message);
    }
}
```
### 消費 Topic 的訊息
```yaml
  jms:
    pub-sub-domain: true    # 設定 true 即開啟 Topic 模式
```

```java
    @JmsListener(destination = ActiveConfig.TOPIC_NAME)
    public void  topicReceive1(String message) {
        System.out.println("消費者 1 接收的 topic 是：" + message);
    }

    @JmsListener(destination = ActiveConfig.TOPIC_NAME)
    public void  topicReceive2(String message) {
        System.out.println("消費者 2 接收的 topic 是：" + message);
    }
```
