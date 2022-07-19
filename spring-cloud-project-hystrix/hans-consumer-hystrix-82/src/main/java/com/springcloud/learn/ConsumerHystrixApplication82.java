package com.springcloud.learn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1，一般Hystrix都用在消费者侧，在Controller层配置，进行服务的降级，熔断，限流等管理。与支付侧配置在service层不同。
 * 2，本模块是一个请求匹配一个降级方法范例，下章示范通配降级方法。
 * 3，启用Hystrix，要在主启动类加@EnableHystrix注解，也可加@EnableCircuitBreaker。
 * 4，消费者侧要使用Feign，因此需加@EnableFeignClients注解。
 * */

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class ConsumerHystrixApplication82 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixApplication82.class, args);
    }

}
