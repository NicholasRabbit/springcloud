package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients     //使用Feign要加此注解
public class SleuthConsumerApplication40 {

    public static void main(String[] args) {
        SpringApplication.run(SleuthConsumerApplication40.class,args);
    }
}
