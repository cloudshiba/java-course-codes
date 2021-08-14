# Sharding-Proxy 分庫分表
### 將訂單表拆成 2 個庫，每個庫 16 張表，進行 CRUD 操作

## DB 設計
- `ec_demo` 拆成 2 個 database: `ec_demo_0`, `ec_demo_1`
- `ec_orders` 拆成 16 張 table: `ec_orders_0`, `ec_orders_1`, ..., `ec_orders_14`, `ec_orders_15`
- [DB Schema](./db/schema.sql)

## Sharding-Proxy config
- [server.yaml](./shardingsphere-proxy-conf/server.yaml)
- [config-sharding.yaml](./shardingsphere-proxy-conf/config-sharding.yaml)
- Sharding-Proxy 跑在 local 3307 port 

