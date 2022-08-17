package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 本模块和8081组成支付集群
 * */

@SpringBootApplication
@EnableDiscoveryClient
public class AliPaymentApplication8082 {

    public static void main(String[] args) {
        SpringApplication.run(AliPaymentApplication8082.class,args);
    }
}
