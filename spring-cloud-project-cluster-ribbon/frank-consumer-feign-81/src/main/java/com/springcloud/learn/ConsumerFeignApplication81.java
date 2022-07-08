package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * OpenFeign使用范例
 * */

@SpringBootApplication
@EnableFeignClients     //要加此注解，不是@EnableEurekaClient了
public class ConsumerFeignApplication81 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignApplication81.class,args);
    }
}
