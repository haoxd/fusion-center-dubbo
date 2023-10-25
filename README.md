
开源界最完整的dubbo3.0示例代码，几乎包含了dubbo3全部功能
===============
采用DDD架构设计，典型的微服务设计思想，可以直接作为dubbo项目的脚手架，吊打市面上所有的dubbo示例工程
===============

当前最新版本：1.0.0

## 后端技术架构
- 基础框架：Spring-Boot-2.7.8 + apache-dubbo-3.2.0 + 自研封装代码+DDD架构设计
- 持久层框架：Mybatis-plus 3.4.1
- 其他：fastjson2, lombok（简化代码）等。

## 开发环境
- 语言：Java 8
- IDE(JAVA)： Eclipse安装lombok插件 或者 IDEA
- 依赖管理：Maven
- 数据库：RDS
- 注册中心：nacos

# 工程代码说明
1、父工程有7个子工程，典型分布式微服务模块化开发

2、fusion-bff 是业务聚合服务实例

3、fusion-restful 北向流量入口，对外暴露接口服务

4、fusion-trip 南向接口服务，调用三方接口服务

5、fusion-common 核心模块的封装

6、fusion-validation 基于jsr303的标准校验实现

# dubbo学习快速入口
- 最权威学习地址：https://github.com/dubbo
- dubbo-admin - Console for monitoring and managing your services.
- dubbo-samples - Samples demonstrating usage of dubbo-java.
- dubbo-go-samples - Samples demonstrating usage of dubbo-go.
- dubbo-spi-extensions - SPI extensions for dubbo-java.
- dubbo-website - Sources of the official website.
- dubbo-awesome - Collection of useful resources for dubbo.
- dubbo-benchmark - Self-service benchmark tool for dubbo-java.



