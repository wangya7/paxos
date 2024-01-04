![](https://nacos.io/img/nacos_white.png)

一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

👉 [官网](https://nacos.io/zh-cn/)

nacos 结合 spring cloud 已经在 [spring-cloud-alibaba](https://github.com/alibaba/spring-cloud-alibaba) 项目中实现，主要分下面两个功能：
* [spring-cloud-starter-alibaba-nacos-config](https://github.com/alibaba/spring-cloud-alibaba/tree/2022.x/spring-cloud-alibaba-starters/spring-cloud-starter-alibaba-nacos-config)
* [spring-cloud-starter-alibaba-nacos-discovery](https://github.com/alibaba/spring-cloud-alibaba/tree/2022.x/spring-cloud-alibaba-starters/spring-cloud-starter-alibaba-nacos-discovery)

项目中也包含对应的案例。

本项目是一个 nacos 的直接运行服务版本，直接运行 NacosServerApplication 的 main 方法即可。

##### 部署过程：
1. 初始 MySQL 脚本，直接导入 当前目录下 db/nacos_init.sql 文件；
2. 修改项目 application.yml 中的 db 的连接信息，也就是对应第1步中的数据；
3. 运行 NacosServerApplication.java 的 main 方法;
4. 打开浏览器 http://127.0.0.1:8848/nacos ，用户名/密码:nacos/nacos 即可登录。


本项目还引入 SpringBoot Admin，所以在 application.yml 中也支持配置其链接信息。