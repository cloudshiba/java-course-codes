# 改善自定義 MQ

## 結構

### 1. my-mq-server

MQ Server 端, 提供 broker, ConsumerGroup 與 Topic 組件

以 `ArrayList` 模擬訊息持久化結構

### 2. my-mq-api

提供客戶端使用的 api，透過 FeignClient，客戶可直接使用執行 RPC

### 3. my-mq-client-producer

生產訊息客戶端，透過 Feign api 生產訊息

### 4. my-mq-client-consumer

消費訊息客戶端，透過 Feign api 消費訊息

## 執行

1. 啟動 `my-mq-server` broker 服務
2. 啟動 `my-mq-client-producer` 與 `my-mq-client-consumer` 生產者/消費者客戶端
3. 透過 `my-mq-client-consumer` 控制台可看到訊息被消費的 log

```shell
2021-09-17 23:13:34.043  INFO 34415 --- [pool-1-thread-1] c.c.m.c.c.MyMqClientConsumerApplication  : 收到訊息：spring-group-1: topic-1-msg:1
2021-09-17 23:13:34.044  INFO 34415 --- [pool-2-thread-1] c.c.m.c.c.MyMqClientConsumerApplication  : 收到訊息：spring-group-2: topic-2-msg:4
2021-09-17 23:13:34.982  INFO 34415 --- [pool-1-thread-1] c.c.m.c.c.MyMqClientConsumerApplication  : 收到訊息：spring-group-1: topic-1-msg:2
2021-09-17 23:13:34.986  INFO 34415 --- [pool-2-thread-1] c.c.m.c.c.MyMqClientConsumerApplication  : 收到訊息：spring-group-2: topic-2-msg:5
2021-09-17 23:13:35.988  INFO 34415 --- [pool-1-thread-1] c.c.m.c.c.MyMqClientConsumerApplication  : 收到訊息：spring-group-1: topic-1-msg:3
2021-09-17 23:13:35.991  INFO 34415 --- [pool-2-thread-1] c.c.m.c.c.MyMqClientConsumerApplication  : 收到訊息：spring-group-2: topic-2-msg:6
2021-09-17 23:13:36.987  INFO 34415 --- [pool-2-thread-1] c.c.m.c.c.MyMqClientConsumerApplication  : 收到訊息：spring-group-2: topic-2-msg:7
```
