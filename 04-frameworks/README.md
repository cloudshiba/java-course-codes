# 第五週作業

## 作業內容

### 作業1
>（选做）使 Java 里的动态代理，实现一个简单的 AOP。

- 未完成

### 作業2
>（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。

- [Java Annotation Config](./spring01/src/main/java/io/kimmking/beandemo/AnnotationBeanWearDemo.java)
- [XML Config](./spring01/src/main/java/io/kimmking/beandemo/XmlBeanWearDemo.java)
- [ComponentScan Config](./spring01/src/main/java/io/kimmking/beandemo/ComponentScanBeanWearDemo.java)

### 作業3
>（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。

- 未完成

### 作業4
>（选做，会添加到高手附加题）
> 
> 4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；
> 
> 4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
> 
> 4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；
> 
> 4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
> 
> 4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。

- 未完成

### 作業5
>（选做）总结一下，单例的各种写法，比较它们的优劣。

- 未完成

### 作業6
>（选做）maven/spring 的 profile 机制，都有什么用法？

- 未完成

### 作業7
>（选做）总结 Hibernate 与 MyBatis 的各方面异同点。

- 未完成

### 作業8
>（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

- 未完成

### 作業9
>（选做）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。

- 未完成

### 作業10
>（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
> 
> 1）使用 JDBC 原生接口，实现数据库的增删改查操作。
> 
> 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
> 
> 3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。

- [Demo](./jdbc-play/src/main/java/com/cloudshiba/jdbcplay/JdbcPlayApplication.java)
