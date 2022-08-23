package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 本模块和8001促成支付集群，都从Nacos配置中心获取配置文件
 * */

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigPaymentClientApplication8002 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigPaymentClientApplication8002.class,args);
    }

}
