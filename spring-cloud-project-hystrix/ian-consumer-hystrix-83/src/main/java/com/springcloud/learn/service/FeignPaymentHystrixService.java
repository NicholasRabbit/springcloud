package com.springcloud.learn.service;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Service
@FeignClient(value="ANN-PAYMENT")   //这里调用支付集群模块
public interface FeignPaymentHystrixService {

    @GetMapping(value = "/payment/get/{id}")
    public abstract CommonResult<PaymentUser> get(@PathVariable("id") Long abc);


    @PostMapping(value = "/payment/insert")
    public abstract CommonResult<PaymentUser> insert(PaymentUser paymentUser);

}
