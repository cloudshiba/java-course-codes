# Redis 情境演練

## Master-Slave Replication Instance 與哨兵機制
演練一主ㄧ從與三個哨兵節點，當主節點無法提供服務時，哨兵節點自動選擇從節點變成新的主節點

## 步驟
- `docker-compose up -d` 啟動所有 redis 節點
- 確認主節點 `docker exec -it redis-master redis-cli info | grep 'role'`
- 確認從節點 `docker exec -it redis-slave redis-cli info | grep 'role'`
- 關閉主節點 `docker stop redis-master`
- 從節點變成新的主節點 `docker exec -it redis-slave redis-cli info | grep 'role'`