package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 把服务注册进Eureka的步骤：
 * 1，pom.xml文件添加Eureka的服务端依赖：“<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>”；
 * 2，配置yaml文件，设置本模块为Eureka的服务端
 * 3，主启动类加@EnableEurekaClient，注意不是@EnableEurekaServer
 * */


@EnableEurekaClient     //加此注解表示把服务注册到Eureka
@SpringBootApplication
public class PaymentApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8081.class,args);
    }

}
