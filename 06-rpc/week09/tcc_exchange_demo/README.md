# TCC 外匯交易處理-使用 dubbo + hmily

## 服務設計
### 帳戶服務
- 美元帳戶服務: 持有美元帳戶資產資訊
- 人民幣帳戶服務: 持有人民幣帳戶資產資訊
### 換匯服務
- 換匯交易服務: 美元與人民幣外匯兌換

## Database 設計
- 帳戶資料庫 accountdb: 採用 [ShardingSphere Proxy](./shardingsphere/config-sharding.yaml) 分兩個庫儲存
- 換匯交易庫 exchange_db: 採用單庫儲存
- [DB schema](./shardingsphere/schema.sql)

## 帳戶服務
### RPC 調用 interface
- [CnyAccountService](./common/src/main/java/com/cloudshiba/tcc/common/account/api/CnyAccountService.java)
- [UsdAccountService](./common/src/main/java/com/cloudshiba/tcc/common/account/api/UsdAccountService.java)
- 各自在方法 `accountDecrease` 與 `accountIncrease` 加上 `@Hmily`，使用 Hmily 進行分布式事務控制用
### RPC 調用實作 Class
- [CnyAccountServiceImpl](./cnyaccount/src/main/java/com/cloudshiba/tcc/cnyaccount/service/CnyAccountServiceImpl.java)
- 對應的方法上加上 `@DubboService(interfaceClass = CnyAccountService.class)` 與 `@HmilyTCC(confirmMethod = "confirmDecrease", cancelMethod = "cancelDecrease")`
- [UsdAccountServiceImpl](./usdaccount/src/main/java/com/cloudshiba/tcc/usdaccount/service/UsdAccountServiceImpl.java)
- 對應的方法上加上 `@DubboService(interfaceClass = UsdAccountService.class)` 與 `@HmilyTCC(confirmMethod = "confirmDecrease", cancelMethod = "cancelDecrease")`

## 換匯服務
- 對外服務入口: [ExchangeService](./exchange/src/main/java/com/cloudshiba/tcc/exchange/service/ExchangeService.java)
- 匯率資訊: [ExchangeRateService](./exchange/src/main/java/com/cloudshiba/tcc/exchange/service/ExchangeRateService.java)
- 執行換匯操作: [ExchangeOperationService](./exchange/src/main/java/com/cloudshiba/tcc/exchange/service/ExchangeOperationService.java)
