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

### 3，使用开启idea热部署DevTools

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

注意：无论是支付模块，还是消费者模块，都是Client，只有Eureka是Server，在主启动类加注解是注意

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

### 10，搭建Eureka集群，服务模块集群总结

**服务模块集群总结**

1，服务模块搭建集群之后，消费者模块就不能访问指定模块的地址了，而是访问在Eureka注册中心注册的服务"ann-payment"，这个服务集群是由两个模块组成的ann-payment-8081/8082，它们的在Eureka注册的名字都是一样的“ann-payment”。在消费者模块ben-consumer的restTemplate对象调用http://ann-payment/get/1等接口，即可实现访问这两个服务模块，注意要在consumer模块获取restTemplate对象是加@LoadBalanced注解，才会执行负载均衡访问；

2，消费者模块或其它模块调用服务模块时，一个模块只能有一个FeignService;

**Eureka模块集群总结**

1，Eureka搭建集群的目的是为了防止一个注册中心瘫痪导致整个服务停用的情况；

2，搭建要领，各Eureka模块相互守望，相互注册。



### 11，使用SpringBoot的actuator来进行Eureka实例的健康检查

![1655817488747](note-images/1655817488747.png)

点击这些超链接之后，默认会显示空的，跳转地址是：http://desktop-p7ure9c/actuator/info  ，可以更改info为health :http://desktop-p7ure9c/actuator/health  进行健康状况检查，也可以查其它参数，后期待查

### 12，Eureka的自我保护

![1655818766990](note-images/1655818766990.png)

![1655818750526](note-images/1655818750526.png)

![1655818996438](note-images/1655818996438.png)

![1655819223582](note-images/1655819223582.png)

**Eureka主页提示的含义，是Eureka的自我保护**

1，如果注册到Eureka的某个服务，暂时不可用了，Eureka不会立即删除该服务，而是保留一段时间，因为有可能是网络卡顿，导致该服务模块并没有按时向注册中心发送心跳信息，因此为了保持高可用，Eureka会启动自我保护模式，保留该服务；

2，Eureka的原则是宁可保留有问题的服务，也不会盲目删除它，另一种形式的反向的宁可错杀一千，不可放过一个；

3，设置方法参照payment-8081模块和Eureka-7001

### 13，Eureka的管理页面设置自定义服务名

![1655889642189](note-images/1655889642189.png)

  [DESKTOP-P7URE9C:ann-payment:8082](http://desktop-p7ure9c:8082/actuator/info) 是Eureka系统默认的名字

```yaml
eureka:
  client:
  ....
  instance:
    instance-id: ann-payment-8081   #在Eureka管理页面显示此名称，不显示默认的带电脑名字的名称
    prefer-ip-address: true       #设置把鼠标放到服务模块名称上时，显示服务ip地址，不是默认的actuator/..，当然也可以访问
```

(1) prefer-ip-address: false

http://desktop-p7ure9c:8082/actuator/info

(2) prefer-ip-address: true

http://172.16.8.230:8081/actuator/info  :这里显示了ip地址

### 14，Ribbon和nginx负载均衡的区别

**一，区别**

Nginx类似于，你去医院，导诊给你安排到哪个科室去看病。

Ribbon类似于，你到了科室，有人给你安排不同的医生给你看病，

![1655992653677](note-images/1655992653677.png)

**二，基本架构示意图**

![1655992933805](note-images/1655992933805.png)

![1655992984528](note-images/1655992984528.png)

**三，Ribbon基本概念**

- eureka-client依赖已经包含ribbon组件，使用时就不用再引入它的依赖了，否则是画蛇添足。

- Ribbon实际就是负载均衡和RestTemplate结合使用起作用。

- RestTemplate的getForObject()和getForEntity()的区别；

- Ribbon是一个软负载均衡的客户端组件，它可以和其他的客户端一起配合使用，也可以和Eureka结合使用；

  ![1655993488498](note-images/1655993488498.png)

### 15，三种服务注册中心的区别

![1656029857769](note-images/1656029857769.png)

![1656029889861](note-images/1656029889861.png)

### 16，Ribbon负载均衡策略

默认有以下七种策略:

![1656423491814](note-images/1656423491814.png)

### 17，自定义Ribbon负载均衡策略步骤，注意事项

**步骤：**

![1656423786625](note-images/1656423786625.png)

**自定义负载配置类所在目录 ：**

![1656423978886](note-images/1656423978886.png)

**注意事项**

![1656423608024](note-images/1656423608024.png)

### 18，Ribbon轮询算法原理

![1656424273046](note-images/1656424273046.png)

### 19，Feign是什么?

![1656686262091](note-images/1656686262091.png)

Feign已停更，目前用OpenFeign

- OpenFeign与Feign原理相同，只是在Feign的基础上做了加强，增加了SpringMVC的支持等;
- Feign是用在消费端的;
- Feign实际就是类似Ribbon和RestTemplate的结合，依赖中包含Ribbon,只是更好用，功能更强大;

**FeignService使用注意事项：**

一个服务模块只能有一个FeignService，否则报错，例：在consumer80模块的FeignService，调用ANN-PAYMENT模块，两个Feign都调ANN-PAYMENT，就会报错：

```txt
报错信息
“The bean 'ANN-PAYMENT.FeignClientSpecification' could not be registered. A bean with that name has already been defined and overriding is disabled”
```

```java
@Service
@FeignClient(value="ANN-PAYMENT",fallback = FeignPaymentHystrixService02Impl.class)
public interface FeignPaymentService {}
```

```java
@Service
@FeignClient(value="ANN-PAYMENT",fallback = FeignPaymentHystrixService02Impl.class)
public interface FeignPaymentService02 {}
```







### 20，Hystrix是什么？

![1657544748106](note-images/1657544748106.png)

**一，是什么？**

它是一个管理服务的组件。

(1)服务熔断(break，相当于保险丝)，服务熔断后调用服务降级并进行友好提示，

(2)服务降级（fallback）的管理工具，还有其他功能;

导致降级的情况：

- 程序运行异常；
- 超时；
- 服务熔断触发服务降级；
- 线程池或信号量打满也会使得服务降级。

(3)服务限流(limit)，如果访问量大，查出服务承受能力，则对请求进行排队限流管理。

**二，为什么用？**

因为在微服务框架中，存在个服务之间连续调用，A调B，B调C...等，如果有某个被调的服务模块发生故障，会导致整个调用的链条停滞，占用资源，导致系统崩溃，因此需要一个工具来管理这些出问题的，把出问题的降级，或阻断，并给出友好提示(fallback)，避免影响整个服务。

**三，底层原理**

Hystrix用到了Tomcat的线程池

**四，用法**

- Hystrix一般用在消费侧，即范例中的consumer-80调用服务的模块，进行服务降级和熔断等。用在消费服务端就是针对消费端的管理，当用户访问消费端时，消费端超时，或出错就由Hystrix进行服务降级或熔断处理。

- 也可用在服务提供的支付模块payment8081/8082等服务提供侧。针对支付服务提供模块管理，当别的模块如消费者模块调用时，支付模块的降级熔断由Hystrix管理。

**五，解决策略**

1，如果超时，则不再等待，启用友好提示，或其它替代方案；

2，服务提供模块出错宕机，要有兜底的服务

### 21，Hystrix熔断详解

**重要功能：服务熔断之后，Hystrix会不断检测服务，一旦服务正常会恢复链路调用**；

1. Hystrix熔断的工作流程：一旦被调用接口出错次数过多就会发生熔断，如输入错误的请求参数等（接口要求输入正数），但是当后面输入正数后，服务的接口还是在熔断的状态，当输入正确的次数达到一定数量后，Hystrix才会使接口恢复正常状态。即从熔断打开(open，服务不可用)变为熔断半开(half  open，服务逐步恢复可用)状态，最后变为熔断关闭(close)，即服务正常调用；

   ![1657806512645](note-images/1657806512645.png)

2. 熔断的步骤：服务降级-->服务熔断-->恢复调用链路;

   **重要功能：服务熔断之后，Hystrix会不断检测服务，一旦服务正常会恢复链路调用**；

   ![1657806298937](note-images/1657806298937.png)

3. 断路器在什么情况下开始作用

![1657806427657](note-images/1657806427657.png)

### 22，Hystrix的dashboard使用方法

1，启动类要设置ServletRegistrationBean

```java
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class ConsumerHystrixApplication83 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixApplication83.class,args);
    }

    /**
     * 若想要Hystrix的dashboard监视本模块，需追加以下方法；
     * 配置ServletRegistrationBean，因为springboot的默认路径不是"/hystrix.stream"，而dashboard页面的规定访问路径格式是：http://localhost:83/hystrix.stream
     * 因此需要加以下配置。
     * */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
```

2，界面填写方式

![1658480990741](note-images/1658480990741.png)



### 23，SpringCloud  Gateway概述

##### 1)，SpringCloud  Gateway是什么？

它是Spring开发的一个网关应用，替代zuul  1.x；

SpringCloud  Gateway是基于WebFlux实现的，而WebFlux底层采用的是高性能Reactor模式的Netty框架。

##### 2)，能干什么？

- 反向代理；
- 鉴权；
- 流量监控；
- 熔断；
- 日志监控……等等



微服务中网关的位置

<img src="note-images/1658151484973.png" alt="1658151484973" style="zoom:60%;" />

##### 3)，SpringGateway特性

![1658413028779](note-images/1658413028779.png)

##### 4)，SpringGateay的几个概念的含义

![1658413488962](note-images/1658413488962.png)

GateWay模块启动时加载断言的配置参数：

![1658837594913](note-images/1658837594913.png)

工作流程：

![1658413739340](note-images/1658413739340.png)

![1658413777702](note-images/1658413777702.png)

##### 5)Gateway的核心逻辑

路由转发，和执行过滤链。

### 24，Gateway配置

- Gateway也是属于eureka-client要注册进Eureka注册中心；

- Gateway的模块不需要spring-boot-starter-web和spring-boot-starter-actuator依赖，因为它只是网关不需要提供web服务；

- 访问路径的区别；

  ![1658750966645](note-images/1658750966645.png)







### 25，SpringCloud Config是什么？怎么用？能干嘛？	

![1658843315411](note-images/1658843315411.png)

**能干嘛？**

![1658759800389](note_images/1658759800389.png)

**为什么用统一配置SpringCloud  Config**

![1658841121330](note-images/1658841121330.png)

分布式配置中心目前有三套方案：

1，SpringCloud Config  + SpringCloud Bus；

2,   Nacos(主流)；

3,  Apollo (携程开源软件)；

### 26，SpringCloud Config配置注意事项

![1658843770679](note-images/1658843770679.png)

1，如图，ConfigServer模块从github上获取各个环境的配置文件，是总的配置中心，在这里统一配置相关参数，避免了微服务中模块过多，每个模块参数单独修改的问题。

  依赖引入，注意有依赖包server

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

2，Client  A/B/C相当于客户端，类似于Eureka  Client，这几个模块需要从Coinfig  Server总配置中心获取统一的配置文件，客户端模块也可以有自己的特殊配置。

同时，客户端模块需要有一个bootstrop.yml配置文件，此文件属于系统级别的，加载优先级高于application.yml，在其内配置引入Config  Server中的配置参数，则客户端启动时会优先引入公共的配置参数，从而达到统一配置的作用。

Client  A/B/C引入依赖，没有server

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

**bootstrap.yml详解：**引入此文件是为了分级管理配置文件

![1658844324807](note-images/1658844324807.png)

### 27，SpringCloud  Bus消息总线概述

是什么？

(1)SpringCloud  Bus配合SpringCloud  Config使用，实现配置的动态刷新，即中心配置模块的配置参数改变了，客户端动态刷新，不需要手动刷新；

(2)支持两种消息代理：RabbitMQ和Kafka

动态刷新流程图

![1658913160668](note-images/1658913160668.png)

能干嘛？

![1658913343739](note-images/1658913343739.png)

为什么叫消息总线？

![1658913390215](note-images/1658913390215.png)

### 28，SpringCloud  Stream概述

**1）是什么？解决了什么问题？**

在大型项目中，有可能JavaEE的后端要与其他模块如大数据的服务模块进行数据交互，而JavaEE后端使用的消息中间件是RabbtiMQ，大数据模块使用的是Kafaka。前者的数据要传输到后者，就得全部推到重来，开发人员也要掌握这两种消息中间件的用法，这样无疑造成工作效率低，且学习成本高。

SpringCloud  Stream提供了一种解决方案，使用Binder绑定器，开发人员只需专注业务逻辑即可，类似于JDBC，无论是MySQL还是Oreacle，我直接调用JDBC接口，语法都是一样的。Stream就是无论是RabbitMQ还是Kafka，我的语法都是一样的。



流程图：

![1660046831186](note-images/1660046831186.png)

![1659570190892](note_images/1659570190892.png)

**2）常用注解及其含义**

![1660045877871](note-images/1660045877871.png)

**3）重复消费问题**：

例如，当订单系统是集群时，用户下一个单，通过RabbitMQ发送消息，两个订单模块都拿到了订单信息，就会重复出单，让消费者付款两次，这显然是不行的。

解决方案：需要Stream的消息分组功能。把具有相同功能的集群放到同一个组，因为同一个组内的服务模块只能有一个获取消息，这就解决了同功能模块重复消费的问题。在application.yml配置文件中自定义配置group的值相同即可。

<img src="note-images/1660050511928.png" alt="1660050511928" style="zoom:80%;" />



![1660049703605](note-images/1660049703605.png)

![1660050314660](note-images/1660050314660.png)

**4）消息的持久化**

在application.yml文件里加了自定义分组的参数以后，也起到了把消息持久化的作用。当某个消息消费模块意外停机没有接收到之前发送的消息时，如果该模块配置的group参数，则重新启动后会接收之前的未读消息。不加group则不会接收。

说明group也起到了把消息持久化的作用。

```yaml
bindings: 
    ....
     group: atguiguA  #设置group参数值   
```



### 29，Sleuth+Zipkin概述

是什么？

SpringCloud  Sleuth是一个分布式链路追踪系统，在微服务中，由于模块较多，相互或者类似于链条式的调用，难免会有个别模块出现延迟等问题，这时需要一个追踪系统，追踪请求。模块配置好Sleuth之后，它会自动日志和服务间的通信中加一些跟踪用的元数据，并结合Zinkin实现聚合汇总这些请求。

引入依赖

SpringCloud 把借鉴了Zipkin，并将其包含到Sleuth的依赖包内。

```xml
 <!--包含了sleuth+zipkin-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

