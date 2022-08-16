package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.FeignPaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 消费者模块没有链接Sleuth，原因待查
 *
 * */

@Controller
@RequestMapping(value = "/sleuth-consumer")
public class SleuthConsumerController40 {

    @Resource
    FeignPaymentService feignPaymentService;

    @GetMapping(value="/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable(value = "id") Long id){
        return feignPaymentService.getUserById(id);
    }

}
