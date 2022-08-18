package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 本支付模块使用Nacos作为注册中心
 * 1，application.yml里需要配置Nacos相关信息
 * 2，Nacos模块是独立的，需要单独启动
 * 3，可配置支付模块集群，做法和使用Eureka时一样，支付集群模块的服务名要一致
 *    payment-8081/8082为集群
 * 4，payment-8081/8082和consumer80使用Nacos作为注册中心，后续的章节把Nacos作为总配置中心。
 * */

@SpringBootApplication
@EnableDiscoveryClient
public class AliPaymentApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(AliPaymentApplication8081.class,args);
    }

}
