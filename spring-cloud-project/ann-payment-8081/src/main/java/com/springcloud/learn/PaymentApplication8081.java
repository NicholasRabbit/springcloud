package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient     //加此注解表示把服务注册到Eureka
@SpringBootApplication
public class PaymentApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8081.class,args);
    }

}
