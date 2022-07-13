package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Hystrix使用范例
 * 1，这里把Hystrix配置在支付侧，即服务提供侧，让Hystrix进行服务降级，熔断等管理，一般配置在消费侧，即consumer模块；
 * 2，Hystrix相关设置见service层代码；
 * 3，使用Hystrix管理服务，除了在Service层加@Hyscommand注解外，主启动类也要加@EnableCircuitBreaker,激活Hystrix，
 *   这里加@EnableCircuitBreaker报错，加@EnableHystrix可以激活
 * */


@SpringBootApplication
@EnableEurekaClient
//@EnableCircuitBreaker
@EnableHystrix
public class PaymentHystrixApplication8083 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixApplication8083.class, args);
    }
}
