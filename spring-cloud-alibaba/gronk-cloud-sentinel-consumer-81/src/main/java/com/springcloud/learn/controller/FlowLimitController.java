package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sentinel")
public class FlowLimitController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/consumer/get")
    @ResponseBody
    public CommonResult<PaymentUser> get(){

        return new CommonResult<PaymentUser>(100,"configInfo:" + configInfo);
    }


}
