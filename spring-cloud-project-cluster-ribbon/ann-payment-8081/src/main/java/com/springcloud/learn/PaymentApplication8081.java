package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 搭建服务模块集群步骤：
 * 1，把8081，8082这两个服务模块配置成集群时，要在consumer-80的ApplicationContextConfig.java的方法中加@LoadBalaned注解，才可实现负载均衡
 * 2，访问本模块地址不是原来的localhost:8081/8082，而是模块名加接口地址：http://ann-payment/get/3
 * */

@EnableEurekaClient     //加此注解，表示本模块为Eureka服务模块
@SpringBootApplication
public class PaymentApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8081.class,args);
    }
}
