package com.springcloud.learn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentFeignHystrixService;
import com.springcloud.learn.service.PaymentFeignHystrixService8083;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/hystrix/consumer")
public class ConsumerHystrixController {

    @Resource
    PaymentFeignHystrixService  paymentFeignHystrixService;

    @Resource
    PaymentFeignHystrixService8083 paymentFeignHystrixService8083;

    /**
     * 1，由于在配置文件进行了配置，及主启动类里加了@EnableHystrix注解，相当于本模块启用了Hystrix，即使这个请求方法不加@HystrixCommand，也会起作用
     *    Hystrix默认请求的超时时间是1秒(见HystrixCommandProperties.java)，因此debug调试时会触发Hystrix降级处理。
     *
     * */
    @GetMapping(value = "/get/{id}")
    @ResponseBody   //别忘了加此注解，返回json格式字符串，否则查询成功了也是报错
    public CommonResult<PaymentUser> get(@PathVariable("id") Long id){
        CommonResult<PaymentUser> commonResult = paymentFeignHystrixService.get(id);
//        CommonResult<PaymentUser> commonResult = paymentFeignHystrixService8083.get(id);
        return commonResult;
    }


    //这里添加成功也会调用熔断方法，待处理？？
    @PostMapping(value = "/insert")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "myFallbackMethod", commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")})
    public CommonResult<PaymentUser> insert(@RequestBody PaymentUser paymentUser){
        CommonResult<PaymentUser> commonResult = paymentFeignHystrixService.insert(paymentUser);
        return commonResult;
    }

    /**
     * 1，服务降级后调用的方法，进行温馨提示
     * 2，服务降级的方法的返回值，形参列表必须与请求方法一致，就会方法重载时找不到该方法，或者否则报错：incompatible ..，
     * */
    public CommonResult<PaymentUser> getFallBackHandler(Long id){
        return new CommonResult(555,"查询方法，进行降级！");
    }

    public CommonResult<PaymentUser> myFallbackMethod(@RequestBody  PaymentUser paymentUser){
        return new CommonResult(444,"服务出错或请求过多，进行降级！");
    }

}
