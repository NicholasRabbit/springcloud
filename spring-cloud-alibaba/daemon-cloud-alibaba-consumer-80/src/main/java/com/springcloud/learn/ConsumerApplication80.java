package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 不使用数据库的话，就不要引入MySQL和Druid等依赖，因为启动时会去配置文件里找数据库配置信息，找不到会报错
 * */

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication80.class,args);
    }
}
