### 1，SpringCloud概念

(1)SpringCloud是什么？

SpringCloud是微服务一套全家桶，提供一个微服务整合方案。其开发是仿造Netflix的一个微服务软件。 SpringBoot是一种微服务技术，相对的，SpringCloud是微服务的一揽子解决方案。

中文文档：https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md

(2)SpringCloud和SpringBoot的版本配合

https://spring.io/projects/spring-cloud 查看官网建议

(3)教程建议版本配置（2020年）

![1653120354628](note-images/1653120354628.png)

(3)SpringCloud各组件选型

最新技术选择：

![1653121452251](note-images/1653121452251.png)

旧版，仅需了解

![1653121549887](note-images/1653121549887.png)

(4)Marin Flower原文

https://www.martinfowler.com/microservices/

```txt
Microservices Guide
In short, the microservice architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies.
-- James Lewis and Martin Fowler (2014)
```

### 2 ，SpringCloud项目构建步骤

约定 > 配置 > 编码

使用Maven构建微服务模块，构建步骤和构建Maven的SpringBoot项目一样

(1)建个父工程：写好pom.xml，包括<dependency-management>等

(2)建模块：右击父工程，新建Maven的Module

(3)写yaml配置文件：规定端口号，项目名称，数据库等信息

(4)写主启动类；

(5)编写业务类的代码。

### 3，使用D开启idea热部署evTools

开发阶段开启热部署，即改动代码不需重启idea，但正式环境不要开启，否则容易出错。

### 4，工程重构

(1)即把各模块重复的代码，提取到一个公用的模块里面，供各模块用，常把实体类，工具类等放到这里

一般都放到commons模块里

(2)然后把commons模块执行 install，安装到本地仓库里，供其它模块调用

### 5，什么是服务注册与发现

Eureka可以有多个，即集群，service provider也可以有多个，根据下面的图理解

![1655469390514](note-images/1655469390514.png)

### 6，新建模块步骤

约定 > 配置 > 编码

1，建Module;

2，改pom.xml;

3，写yaml;

4，主启动类；

5，编写业务代码。

### 7，Eureka集群工作原理

互相注册，相互守望

![1655721491044](note-images/1655721491044.png)

因为Eureka模块的application.yml里：hostname: localhost，所以再多加一个Eureka服务注册模块的话，需要在hosts文件中配置映射，使localhost能映射多个eureka模块。

```yaml
#配置Eureka
eureka:
  instance:
    #hostname: localhost
    hostname: eureka7001.com
```

这样配置实际是让localhost当一个转发器，当访问eureka7001.com:7001端口的时候，访问的是localhost:7001端口，7002同理。

```txt
127.0.0.1  eureka7001.com
127.0.0.1  eureka7002.com
```

### 8，微服务注册后的访问问题

1）微服务模块注册后，例如支付模块注册到Eureka服务注册中心，别的模块调用实际还是直接获取该支付模块的接口并调用，并不是调用Eureka中心，再让Eureka中心调支付模块。别的模块只是从Eureka获取了访问地址。

2）后面加负载均衡之后，消费者模块调用的是Eureka提供的注册服务名，然后由负载均衡的组件进行轮询访问提供相同服务的多个模块，类似于Nginx的负载均衡；

### 9，多个idea同时启动微服务时注意问题

有的端口，注册会互相干扰，应只开一个idea，或关闭其它idea微服务

### 10，Ribbon和nginx负载均衡的区别

Nginx类似于，你去医院，导诊给你安排到哪个科室去看病。

Ribbon类似于，你到了科室，有人给你安排不同的医生给你看病，

![1655992653677](note_images/1655992653677.png)

基本架构示意图

![1655992933805](note_images/1655992933805.png)

![1655992984528](note_images/1655992984528.png)

- eureka-client依赖已经包含ribbon组件，使用时就不用再引入它的依赖了，否则是画蛇添足。

- Ribbon实际就是负载均衡和RestTemplate结合使用起作用。

- RestTemplate的getForObject()和getForEntity()的区别；

  ![1655993488498](note_images/1655993488498.png)

### 11，三种服务注册中心的区别

![1656029857769](note_images/1656029857769.png)

![1656029889861](note_images/1656029889861.png)