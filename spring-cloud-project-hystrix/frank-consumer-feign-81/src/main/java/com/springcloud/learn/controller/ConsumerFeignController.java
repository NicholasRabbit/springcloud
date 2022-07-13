package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentFeignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/feign-consumer")
public class ConsumerFeignController {

    @Resource
    PaymentFeignService paymentFeignService;

    @GetMapping(value="/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable(value = "id") Long id){
        return paymentFeignService.getById(id);
    }

    //Feign模块调用超时测试，设置等待3秒
    //超时会报错：java.net.SocketTimeoutException: Read timed out
    @GetMapping(value="/getTimeout/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getTimeoutById(@PathVariable(value = "id") Long id){
        return paymentFeignService.getTimeoutById(id);
    }

}
