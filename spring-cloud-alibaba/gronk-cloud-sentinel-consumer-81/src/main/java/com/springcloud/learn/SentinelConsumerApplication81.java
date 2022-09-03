package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1,Sentinel采用的是懒加载机制，虽然本服务由Sentinel监控，但是不访问本服务的接口，Sentinel是不会出现任何信息的
 * */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SentinelConsumerApplication81 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelConsumerApplication81.class,args);
    }

}
