package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 一，集群版微服务
 * 1，Eureka服务注册中心可以有多个，防止有个别的挂机，导致服务终端
 * 2，服务提供模块也可以有多个，提供相同服务，由Eureka来管理负载均衡
 * 二，把其他服务注册进Eureka步骤：
 * 1，在服务模块引入eureka-client依赖，具体见其它模块代码
 * 2，在服务模块的yaml配置文件追加Eureka相关配置
 * 3，在服务模块主启动类加@EnableEurekaClient注解
 * 三，启动时，先启动Eureka服务注册中心模块，再启动提供服务的模块，最后是服务调用的模块
 *
 * 四，Eureka集群配置：
 * 原则：互相注册，相互守望
 * 1，把一个Eureka注册中心组测到另一个Eureka里面，另一个再注册到这个里，即多个Eureka互相注册；
 * 2，服务模块注册到所有的Eureka里；
 * 3，本地测试还要设置../etc/hosts文件，进行映射，结合笔记配置
 *    配置好之后，访问地址就变成了http://eureka7001.com:7001
 * 4，在服务模块的配置文件application.yml进行配置，注册到多个Eureka里
 *    “defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka”
 * */


@EnableEurekaServer  //Eureka注册中心要加此注解
@SpringBootApplication
public class EurekaApplication7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication7001.class,args);
    }
}
