package com.springcloud.learn.controller;


import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.FeignPaymentHystrixService02;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 本Controller层，不加@DefaultProperties或@HystrixCommand注解，由FeignService的实现类统一处理降级
 * */

@Controller
@RequestMapping(value = "/hystrix/feign-fallback/consumer")
public class IanConsumerHystrixController02 {

    @Resource
    FeignPaymentHystrixService02 feignPaymentHystrixService02;

    @GetMapping(value = "/getErrorById/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getErrorById(@PathVariable("id") Long id){
        CommonResult<PaymentUser> commonResult = feignPaymentHystrixService02.getErrorById(id);
        return commonResult;
    }

}
