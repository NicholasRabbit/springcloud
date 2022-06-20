package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 一，Eureka本身有两大组件，
 * server:服务注册中心
 * client:服务端，一般不用它本身的服务端，用其它自定义的服务模块
 * 二，把其他服务注册进Eureka步骤：
 * 1，在服务模块引入eureka-client依赖，具体见其它模块代码
 * 2，在服务模块的yaml配置文件追加Eureka相关配置
 * 3，在服务模块主启动类加@EnableEurekaClient注解
 * 三，启动时，先启动Eureka服务注册中心模块，再启动提供服务的模块，最后是服务调用的模块
 * */


@EnableEurekaServer    //Eureka的服务中心启动类，要加此注解
@SpringBootApplication
public class EurekaApplication7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication7001.class,args);
    }
}
