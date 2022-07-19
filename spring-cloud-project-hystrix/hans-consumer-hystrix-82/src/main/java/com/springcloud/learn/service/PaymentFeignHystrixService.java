package com.springcloud.learn.service;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 消费者侧的Feign，在这里配置Hystrix，进行服务管理，执行降级，熔断，限流等操作
 * */

@Service
@FeignClient(value="ANN-PAYMENT")   //这里调用支付集群模块
public interface PaymentFeignHystrixService {

    @GetMapping(value = "/payment/get/{id}")
    public abstract CommonResult<PaymentUser> get(@PathVariable("id") Long abc);


    @PostMapping(value = "/payment/insert")
    public abstract CommonResult<PaymentUser> insert(PaymentUser paymentUser);

}
