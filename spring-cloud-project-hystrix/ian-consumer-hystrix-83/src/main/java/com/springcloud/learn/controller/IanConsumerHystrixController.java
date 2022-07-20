package com.springcloud.learn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;

import com.springcloud.learn.service.FeignPaymentHystrixService;
import com.springcloud.learn.service.FeignPaymentHystrixService8083;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *本例配置统一处理降级的方法
 * 1，@DefaultProperties使用注意：
 *   (1)根据源码中的注解可知，所有进行降级的请求方法的返回值必须与defaultFallback属性指定的统一处理降级的方法的返回值相同;
 *   (2)该属性指定的降级方法没有形参列表；
 *   (3)所有使用统一降级方法的请求需加@HystrixCommand注解，且无需写属性
 * 2，@DefaultProperties内的属性和@HystrixCommang一样，用法也一样
 * 3，如果有的请求方法不想用全局规定的处理降级方法，和超时限制等，还直接加@HystrixCommand注解，自己定义相关属性值即可；
 * */

@Controller
@RequestMapping(value = "/hystrix/consumer")
@DefaultProperties(defaultFallback = "allFallBackHandler",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")})
public class IanConsumerHystrixController {

    @Resource
    FeignPaymentHystrixService feignPaymentHystrixService;

    @Resource
    FeignPaymentHystrixService8083 feignPaymentHystrixService8083;

    //不进行降级处理的方法，做对比
    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> get(@PathVariable("id") Long id){
        CommonResult<PaymentUser> commonResult = feignPaymentHystrixService.get(id);
        return commonResult;
    }

    /**一，该请求随有超时，但是@DefaultProperties规定的最大等待时间时5秒，因此本请求方法不会触发服务降级*/
    @HystrixCommand
    @GetMapping(value = "/getTimeout/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getTimeout(@PathVariable("id") Long id){
        //线程睡眠3秒
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonResult<PaymentUser> commonResult = feignPaymentHystrixService.get(id);
        return commonResult;
    }

    /**二，本请求睡眠6秒，超过@DefaultProperties里规定的最大时间，因此会触发降级*/
    @HystrixCommand
    @GetMapping(value = "/getTimeoutWithFallback/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getTimeout2(@PathVariable("id") Long id){
        //线程睡眠6秒
        try {
            TimeUnit.MILLISECONDS.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonResult<PaymentUser> commonResult = feignPaymentHystrixService.get(id);
        return commonResult;
    }

    /**三，如果不想使用全局的降级处理，则还是按照之前的方式，加@HystrixCommand注解，自己定义属性值即可。 */
    @PostMapping(value = "/insert")
    @ResponseBody                   //这里的"myFallbackMethod"就是处理降级的方法名
    @HystrixCommand(fallbackMethod = "myFallbackMethod", commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")})
    public CommonResult<PaymentUser> insert(@RequestBody PaymentUser paymentUser){
        CommonResult<PaymentUser> commonResult = feignPaymentHystrixService.insert(paymentUser);
        return commonResult;
    }
    /**个人自定义的降级方法*/
    public CommonResult<PaymentUser> myFallbackMethod(@RequestBody  PaymentUser paymentUser) {
        return new CommonResult(444, "服务出错或请求过多，进行降级！");
    }

    /**全局处理降级的方法*/
    public CommonResult<PaymentUser> allFallBackHandler(){
        return new CommonResult<PaymentUser>(444,"全局降级处理方法==>服务已降级！");
    }


}
