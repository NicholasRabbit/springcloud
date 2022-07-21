package com.springcloud.learn;

import com.springcloud.myRibbon.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 *Ribbon使用在消费模块的，即调用服务端的
 *
 * 一，Ribbon负载均衡范例
 * 1，Ribbon的依赖已经包含在“spring-cloud-starter-netflix-eureka-client”依赖中了，不需重新引入；
 * 2，Ribbon默认的负载均衡策略是轮询
 * 其它参照SpringCloud笔记
 * 二，Ribbon的工作流程
 * 1，第一步先进行EurekaServer的选择，选择负载较小的Server；
 * 2，第二步，根据用户指定的策略，在EurekaServer注册中心选择一个地址，进行调用；
 * Ribbon提供多种策略，如轮询，随机，根据相应时间加权等
 *
 * 三，自定义Ribbon的负载均衡策略，见myRibbon包内设置类笔记
 *
 * 四，手写轮询算法，模拟Ribbon源码，加深理解
 *    这个手写的轮询算法类必须放在可被扫描到的地方
 * */


@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "ANN-PAYMENT", configuration = MyRibbonRule.class)   //使用自定义的负载均衡策略要加此注解，并设定进行负载均衡的服务，选定自定义的负载均衡规则MyRibbonRule.java
public class ConsumerApplication80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication80.class,args);
    }
}
