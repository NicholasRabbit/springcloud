package com.springcloud.learn.service;


import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "VI-SLEUTH-ZIPKIN-PAYMENT-3001")
public interface FeignPaymentService {

    @GetMapping(value = "/sleuth-payment/get/{id}")
    public abstract CommonResult<PaymentUser> getUserById(@PathVariable(value="id") Long id);

}
