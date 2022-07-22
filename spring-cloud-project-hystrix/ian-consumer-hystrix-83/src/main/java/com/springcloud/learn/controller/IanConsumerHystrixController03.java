package com.springcloud.learn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.FeignPaymentHystrixService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 熔断范例
 * 1，熔断也需指定熔断处理方法，与降级同理；
 * 2，(重点)Hystrix熔断服务后，不是始终保持熔断打开的状态。当始终有请求指向被熔断的服务且该服务的接口不再报错时，它会把熔断器改成半开半闭的状态，当一定数量的请求都不报错时，
 *   会彻底关闭断路器，恢复服务的链路调用。
 *
 * */
@Controller
@RequestMapping(value="/hystrix/circuit-breaker")
public class IanConsumerHystrixController03 {


    @Resource
    FeignPaymentHystrixService feignPaymentHystrixService;


    /**1,服务熔断方法范例，注意打断点会触发熔断，即使方法不报错，因为超时，Hystrix默认限时1秒。
     *   也可修改等待时间，见下文范例；
     * 2, http://localhost:83/hystrix/circuit-breaker/circuitBreakerGet/1
     *   当输入负数时会触发熔断，再输入正数还是熔断的状态，当输入正数次数够多时，熔断器状态变化： 全开-->半开-->关闭(服务正常调用)
     * */
    @HystrixCommand(fallbackMethod = "myCircuitBreakerMethod",commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //设置是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  //请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   //时间窗口期。作用待查
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),    //设置失败率到达多少后跳闸熔断
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")  //设置允许等待的时间，与fallback设置同理
    })
    @GetMapping(value = "/circuitBreakerGet/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> circuitBreakerGet(@PathVariable("id") Long id){
        if(id < 0){
           throw new RuntimeException("id不可小于零");    //模拟接口出错，触发熔断。
        }
        /*CommonResult<PaymentUser> commonResult = feignPaymentHystrixService.get(id);
        return commonResult;*/
        return new CommonResult<>(200,"ok");
    }


    /**个人自定义的熔断方法*/
    public CommonResult<PaymentUser> myCircuitBreakerMethod(Long id) {
        return new CommonResult(444, "服务出错，已进行熔断！");
    }


}
