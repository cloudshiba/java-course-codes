# Spring 操作 Kafka Cluster

## 環境搭建
- [使用 docker 搭建 Kafka Cluster](./docker-compose.yml)
- `docker-compose up -d` 啟動環境

## Spring Kafka Client
- 導入到 IDE 後啟動
- Terminal 發送消息 `curl -X POST http://localhost:9000/kafka/publish -d message='I am publishing a message!'` 
