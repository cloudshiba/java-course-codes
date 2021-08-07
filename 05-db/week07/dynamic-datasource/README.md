# 作業 9 讀寫分離 - 動態切換 DataSource 版本 1.0
## 使用 Spring Data JPA，並使用自定義 Annotation，透過 AbstractRoutingDataSource 動態切換 DataSource

## DataSource 角色
- ### Primary DataSource: 主要寫入的 DB
- ### Secondary DataSource: 主要讀取的 DB

## 動態切換 DataSource
- 自定義 [`TargetDataSource`](./src/main/java/com/clooudshiba/dynamicdatasource/datasource/TargetDataSource.java)，依據參數 `PRIMARY` 或 `SECONDARY` 設定使用的 DataSource
- 自定義 [`DynamicDataSourceChangeAspect`](./src/main/java/com/clooudshiba/dynamicdatasource/datasource/DynamicDataSourceChangeAspect.java)，透過 AOP 機制動態切換 DataSource

## DB 環境設定
- 建立兩個 MySQL instance
- 個別建立名稱為 ecdemo 的 Database
- DDL Schema 參考 [schema.sql](./src/main/resources/db/schem.sql)