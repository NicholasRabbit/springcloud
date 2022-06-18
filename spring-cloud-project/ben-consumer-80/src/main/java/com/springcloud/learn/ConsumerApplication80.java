package com.springcloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1,从这个服务里调用ann-payment模块，进行数据的增删改查；
 * 2,这里不用写服务层，持久化层了，只写控制层就行了；
 * */

@SpringBootApplication
public class ConsumerApplication80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication80.class,args);
    }

}
