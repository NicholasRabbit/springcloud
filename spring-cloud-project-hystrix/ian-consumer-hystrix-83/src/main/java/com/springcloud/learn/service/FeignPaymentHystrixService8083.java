package com.springcloud.learn.service;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "GABRIELLE-PAYMENT-HYSTRIX-8083")
public interface FeignPaymentHystrixService8083 {

    @GetMapping(value = "/payment-hystrix/get/{id}")
    public abstract CommonResult<PaymentUser> get(@PathVariable(value="id") Long id);
}
