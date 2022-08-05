package com.springlcoud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 访问本模块：http://localhost:7700/master/config-dev.yml: master是分支名，在application.yml里进行设置
 * 1,使用Bus消息总线配合RabbitMQ可实现一次请求所有模块都刷新配置信息的效果，优化上章节手动请求模块刷新配置信息的做法；
 * 2,实现全部模块动态刷新：可以发送请求:curl -X POST "http://localhost:7700/actuator/bus-refresh"到总配置中心，其它客户端就会动态刷新配置信息。
 *   RabbitMQ监控页面会有曲线波动，说明由消息发送了。
 *   也可发送到其中一个客户端，可实现其它所有客户端同步刷新的效果。一般发送请求到总配置中心。
 *
 * * */


@SpringBootApplication
@EnableConfigServer   //总配置中心加此注解，客户端不用加
public class ConfigServerBusApplication7700 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerBusApplication7700.class,args);
    }


}
