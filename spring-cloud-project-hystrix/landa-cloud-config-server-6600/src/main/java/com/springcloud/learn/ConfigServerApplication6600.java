package com.springcloud.learn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 1，在配置文件配置好之后，访问git上config-dev.yml的地址是：
 *    http://localhost:6600/master/config-dev.yml: master是分支名，在application.yml里进行设置
 * 2，config-dev.yml在仓库根目录下，即spring-cloud-config（仓库名）总目录下才可访问，如果放到spring-cloud-config/config/xxx.yml目录下则无法访问，原因待查；
 * 3，如果git中只有一个配置文件，且名字是application.yml/properties，这是访问路径中的http://localhost:6600/master/xxx，最后的xxx随便输也可访问。
 * 未解决问题：
 * git仓库的配置文件改成version=2之后，通过浏览器访问还是version=1,重启也不管用，原因待查。
 * */


@SpringBootApplication
@EnableConfigServer     //总配置中心要加此注解
public class ConfigServerApplication6600 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication6600.class,args);
    }
}
