package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 1，本支付模块从Nacos总配置中心获取数据库等相关信息，如果集群中模块多的话，以后修改信息的话，就不用每个修改了。
 * 2，@RefreshScope注解同时要在Controller的类加上，才可接收配置的动态刷新，与Spring Cloud Config/Bus一样，Nacos抄袭的前两者。
 *    加此注解后，不用像使用Bus一样手动请求总配置中心或每个模块，例，curl -X POST "http://localhost:7700/actuator/bus-refresh，
 *    Nacos会自动通知每个客户端模块。而客户端模块不必重新启动。
 * 3，Nacos既是服务注册中心，又是总配置中心，= Eureka + Config + Bus。
 *
 * */

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigPaymentClientApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigPaymentClientApplication8001.class,args);
    }
}
