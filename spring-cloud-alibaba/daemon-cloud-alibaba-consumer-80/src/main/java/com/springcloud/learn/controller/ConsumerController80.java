package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 浏览器访问：http://localhost/nacos/consumer/get/1。
 * 消费者模块注册进Nacos注册中心，使用RestTemplate调用支付模块的集群，
 * Nacos内置了Ribbon，默认按照轮询算法访问支付模块，和之前单纯使用Ribbon一样。
 * */

@Controller
@RequestMapping(value = "/nacos/consumer")
public class ConsumerController80 {

    @Resource
    private RestTemplate restTemplate;

    @Value("${payment.server-url}")
    private String PAYMENT_URL;
    @Value("${payment.config-server-url}")
    private String CONFIG_PAYMENT_URL;

    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getUserById(@PathVariable(value="id") String id){
        //从这里通过http工具调用ann-payment模块的服务，实现了跨模块调用获取数据；
        //   使用getForObject(..)方法；
        CommonResult<PaymentUser> commonResult = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(commonResult == null){
            return new CommonResult<>(444,"无此数据");
        }else{
            return commonResult;
        }
    }

    /**访问8001/8002集群
     * 注意，因为这两个集群yml里设置了context-path，所以访问路径是：服务名 + context-path + uri
     * */
    @GetMapping("/get-config/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getConfigById(@PathVariable(value="id") String id){
        //从这里通过http工具调用ann-payment模块的服务，实现了跨模块调用获取数据；
        //   使用getForObject(..)方法；
        CommonResult<PaymentUser> commonResult = restTemplate.getForObject(CONFIG_PAYMENT_URL + "/nacos-config/client" + "/payment/get/" + id, CommonResult.class);
        if(commonResult == null){
            return new CommonResult<>(444,"无此数据");
        }else{
            return commonResult;
        }
    }



}
