package com.springcloud.myRibbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置自定义负载均衡规则注意事项：
 * 本例设置为随机策略
 * 1，自定义的负载均衡配置类必须在@ComponnetScan所扫描不到的地方，即不要跟主启动类在同目录下，
 *    否则这个自定义策略会被所有Ribbon客户端共享，起不到特殊定制的作用了。
 * 2，要在主启动类加@RibbonClient注解，并设置好服务名和自定义配置类的名字。
 * */

@Configuration
public class MyRibbonRule {

    //注意这个方法的名字不要写的和类名一样myRibbonRule，因为这里@Bean生成的RandomRule.java对象默认id是方法名，而Spring框架生成这个配置类的id也是myRibbonRule
    @Bean
    public IRule myRandomRule(){
        //return new RoundRobinRule();  //默认的轮询算法
        return new RandomRule();  //返回随机策略算法
    }
}
