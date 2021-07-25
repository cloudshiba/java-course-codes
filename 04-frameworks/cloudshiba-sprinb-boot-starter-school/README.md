# 自定義的 Spring Boot Starter

## 安裝

- 在 pom.xml 宣告
```xml
<dependency>
	<groupId>com.cloudshiba</groupId>
	<artifactId>my-sprinb-boot-starter-school</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```

- Maven 安裝

會在當前 `target` 路徑產生 jar 檔，也會在本機 Maven 倉庫產生對應的 jar 檔。
```bash
mvn install
```

## 組成類別
- [Student](./src/main/java/com/cloudshiba/core/Student.java)
- [Klass](./src/main/java/com/cloudshiba/core/Klass.java)
- [School](./src/main/java/com/cloudshiba/core/School.java)
- [SchoolConfig](./src/main/java/com/cloudshiba/config/SchoolConfig.java)

## spring.factories
```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.cloudshiba.config.SchoolConfi
```
