package com.springcloud.learn;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class ConsumerHystrixApplication83 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixApplication83.class,args);
    }

    /**
     * 若想要Hystrix的dashboard监视本模块，需追加以下方法；
     * 配置ServletRegistrationBean，因为springboot的默认路径不是"/hystrix.stream"，而dashboard页面的规定访问路径格式是：http://localhost:83/hystrix.stream
     * 因此需要加以下配置。
     * */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
