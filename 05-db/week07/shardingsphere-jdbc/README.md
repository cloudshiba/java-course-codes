# 作業 10 - 讀寫分離 2.0 使用 ShardingSphere-jdbc

## 使用 Sharding-JDBC 5.0.0-alpha 實作讀寫分離需求
### 版本資訊:
- Spring Boot 2.3.0.RELEASE
- Spring Data JPA
- Sharding-JDBC 5.0.0-alpha

## 環境設定
- 建立兩個 MySQL instance
- 個別建立名稱為 ecdemo 的 Database
- DDL Schema 參考 [schema.sql](./src/main/resources/db/schema.sql)

## 有遇到與官方版本不一致的地方
按照官方說明設定
```yaml
spring:
  shardingsphere:
    datasource:
      names: write,read
      write:
        driver-class-name: com.mysql.jdbc.Driver
        password: ''
        type: org.apache.commons.dbcp.BasicDataSource
        url: jdbc:mysql://localhost:3306/master
        username: root
      read:
        driver-class-name: com.mysql.jdbc.Driver
        password: ''
        type: org.apache.commons.dbcp.BasicDataSource
        url: jdbc:mysql://localhost:3306/slave0
        username: root
    masterslave:
      name: ms
      load-balance-algorithm-type: round_robin
      master-data-source-name: write
      slave-data-source-names: read
    props:
      sql:
        show: true
```
卻一直出現 `Caused by: java.util.NoSuchElementException: No value bound` 錯誤

參考網路上的文章修改成以下設定總算可以正常執行
```yaml
spring:
  shardingsphere:
    datasource:
      common:
        driver-class-name: com.mysql.cj.jdbc.Driver
        password: 12345678
        type: com.zaxxer.hikari.HikariDataSource
        username: root
      write:
        jdbc-url: jdbc:mysql://localhost:3306/ecdemo?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
      read:
        jdbc-url: jdbc:mysql://localhost:3307/ecdemo?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8
      names: write,read
    rules:
      replica-query:
        data-sources:
          rw:
            load-balancer-name: round-robin
            primary-data-source-name: write
            replica-data-source-names: read
        load-balancers:
          round-robin:
            props:
              sql:
                show: tru
            type: ROUND_ROBIN
```