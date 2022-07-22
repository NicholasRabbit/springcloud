package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 1,Hystrix的dashboard只有一个启动类，没有业务处理层；
 * 2,访问路径：http://localhost:90/hystrix ，注意加:hystrix；
 * 3,在dashboard页面对应位置输入要检测的服务模块名称即可；
 * */

@SpringBootApplication
@EnableHystrixDashboard
public class DashboardApplication90 {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication90.class,args);
    }

}
