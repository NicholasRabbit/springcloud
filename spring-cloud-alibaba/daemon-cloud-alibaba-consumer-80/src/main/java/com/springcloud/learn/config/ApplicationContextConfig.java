package com.springcloud.learn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 写一个配置类，把RestTemplate注入容器
 * */
@Configuration
public class ApplicationContextConfig {

    @Bean  //方法名就是对象的属性名
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
