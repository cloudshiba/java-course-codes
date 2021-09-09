# Redis 情境演練

## Redis Cluster
將 6 台 Redis Server 組成 Cluster

## 步驟
- 啟動服務並建立 Cluster `ip=$(ipconfig getifaddr en0) docker-compose up -d`
- 查看單一節點 Cluster 狀態 `docker exec -it redis_cluster_redis-node1_1 bash -c 'redis-cli -p 7000 cluster info'`
- 查看 Cluster 節點資訊`docker exec -it redis_cluster_redis-node1_1 bash -c 'redis-cli -p 7000 cluster nodes'`
- 進入 Cluster 互動模式 `docker exec -it redis_cluster_redis-node1_1 bash -c 'redis-cli -p 7000 -c'`
