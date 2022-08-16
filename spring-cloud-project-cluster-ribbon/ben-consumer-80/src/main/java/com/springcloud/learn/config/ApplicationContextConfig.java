package com.springcloud.learn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @LoadBalanced    //服务模块是集群时要加此注解，进行负载均衡，默认是轮询的的方式，否则只访问一个模块，抛出异常UnknownHostException
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
