package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/consumer")
public class ConsumerController {

    //public static final String PAYMENT_URL = "http://localhost:8081";    //单服务模块访问地址写法
    public static final String PAYMENT_URL = "http://ANN-PAYMENT";   //访问集群时的写法，这个路径是集群中多个模块的设置好的共同的名字

    @Resource   //这里使用@Resource注解也可以
    private RestTemplate restTemplate;


    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getUserById(@PathVariable(value="id") String id){
        //一，从这里通过http工具调用ann-payment模块的服务，实现了跨模块调用获取数据
        CommonResult<PaymentUser> commonResult = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);  //注意地址拼写
        return commonResult;
    }

    //进行添加数据
    @PostMapping(value="/insert")
    @ResponseBody
    public CommonResult<PaymentUser> insert(PaymentUser paymentUser){
        //RestTemplate传输的数据格式，可以为form，或者json
        CommonResult insertResult = restTemplate.postForObject(PAYMENT_URL + "/payment/insert", paymentUser, CommonResult.class);
        return insertResult;
    }

}
