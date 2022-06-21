package com.springcloud.learn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @LoadBalanced    //服务模块是集群时要加此注解，否则只访问一个模块
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
