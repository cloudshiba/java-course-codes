# 設計 RPC 框架
## 1 尝试将服务端写死查找接口实现类变成泛型和反射
- [RpcfxInvoker](./rpcfx-core/src/main/java/io/kimmking/rpcfx/server/RpcfxInvoker.java)

## 2 尝试将客户端动态代理改成 AOP 添加异常处理
- [Rpcfx](./rpcfx-core/src/main/java/io/kimmking/rpcfx/client/Rpcfx.java)

## 3 尝试使用 Netty+HTTP 作为 client 端传输方式
- [NettyRpcClient](./rpcfx-core/src/main/java/io/kimmking/rpcfx/client/NettyRpcClient.java)
