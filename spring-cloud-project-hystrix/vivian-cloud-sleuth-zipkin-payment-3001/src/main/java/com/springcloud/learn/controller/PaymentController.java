package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 1,SpringCloud  Sleuth是一个分布式链路追踪系统，在微服务中，由于模块较多，相互或者类似于链条式的调用，难免会有个别模块出现延迟等问题，
 * 这时需要一个追踪系统，追踪请求。模块配置好Sleuth之后，它会自动日志和服务间的通信中加一些跟踪用的元数据，并结合Zinkin实现聚合汇总这些请求。
 * 2,注意Zipkin是一个单独的jar包，需要倒官网下载启动，java -jar zipkin-server-2.23.18-exec.jar，默认访问地址是：http://localhost:9411
 *   见application.yml中的配置。
 * */

@Controller
@RequestMapping(value="/sleuth-payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable(value = "id") Long id){
        PaymentUser paymentUser = paymentService.getUserById(id);
        if(paymentUser != null){
            return new CommonResult<PaymentUser>(200,"success,port:" + serverPort,paymentUser);
        }
        return new CommonResult<>(444,"failure,port" + serverPort,null);
    }


}
