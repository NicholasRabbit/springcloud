package com.springcloud.learn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer     //总配置中心要加此注解
public class ConfigServerApplication6600 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication6600.class,args);
    }
}
