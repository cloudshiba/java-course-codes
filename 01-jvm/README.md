# 第一週作業

## 作業內容

### 作業1
> （选做）自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。

- [Hello class](./java-course-codes/src/main/java/com/cloudshiba/javacoursecodes/bytecode/Hello.java)
- [Hello bytecode](./java-course-codes/Hello_bytecode.md)
- [Hello class decompile](./java-course-codes/Hello_decompile.md)
- [Hello opcode](./java-course-codes/Hello_opcode.md)

### 作業2
> （必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

- [XlassLoader.java](./java-course-codes/src/main/java/com/cloudshiba/javacoursecodes/classloader/XlassLoader.java)

### 作業3
> （必做）画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。

- [圖示](./作業3_jvm_process_memory_structure_20210627.png)

### 作業4
> （选做）检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。
> 注意：如果没有线上系统，可以自己 run 一个 web/java 项目。

- 未完成

### 作業5
> （选做）本机使用 G1 GC 启动一个程序，仿照课上案例分析一下 JVM 情况。

- 未完成
