package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 1，本支付模块从Nacos总配置中心的配置文件获取数据库等相关信息，如果集群中模块多的话，以后修改信息的话，就不用每个修改了。
 * 2，@RefreshScope注解同时要在Controller的类加上，才可接收Nacos总配置中心的动态刷新，
 *    加此注解后，不用像使用Bus一样手动请求总配置中心或每个模块，与Spring Cloud Config/Bus用法一样，Nacos抄袭的前两者，但更好用了。
 *    Nacos会自动通知每个客户端模块，不用手动发curl -X POST "http://localhost:7700/actuator/bus-refresh。
 *    而客户端模块不必重新启动。
 * 3，Nacos既是服务注册中心，又是总配置中心，= Eureka + Config + Bus。
 * 4，注意，配置集群以后，因为spring.application.name的值变更了，所以Nacos配置中心的配置文件名字要调成为该值
 * 5，组成集群后把服务名注册在Nacos注册中心，跟Eureka中注册服务名一样，外部不能通过直接输入服务名访问集群了，需要在80模块使用RestTemplate访问，然后浏览器访问80模块的接口
 *   访问路径注意加context-path: 服务名 + context-path + uri
 * 6，使用Nacos的配置文件是，注意application.yml里的active激活的值
 * */

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigPaymentClientApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigPaymentClientApplication8001.class,args);
    }
}
