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
import java.util.concurrent.TimeUnit;

/**
 * 总结：
 * 使用@HystrixCommand注解指定降级方法，每个接口都要单独配置方法，如此会造成代码冗余；
 * 下节配置全局处理服务降级的方法。
 * */

@Controller
@RequestMapping(value = "/hystrix/consumer")
public class HansConsumerHystrixController {

    @Resource
    PaymentFeignHystrixService  paymentFeignHystrixService;

    @Resource
    PaymentFeignHystrixService8083 paymentFeignHystrixService8083;

    /**
     * 1，由于在配置文件进行了配置，及主启动类里加了@EnableHystrix注解，相当于本模块启用了Hystrix，即使这个请求方法不加@HystrixCommand，也会起作用
     *    Hystrix默认请求的超时时间是1秒(见HystrixCommandProperties.java)，当本消费这模块调用支付模块时，支付模块响应超时或出错则会触发Hystrix降级处理(Payment模块debug时间长也会)，
     *    即使没有处理降级的方法，也会返回系统默认的降级提示页面。
     * 2，但是本消费者模块，这里的get方法设置超时，不会触发降级。
     * */
    @GetMapping(value = "/get/{id}")
    @ResponseBody   //别忘了加此注解，返回json格式字符串，否则查询成功了也是报错
    public CommonResult<PaymentUser> get(@PathVariable("id") Long id){
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonResult<PaymentUser> commonResult = paymentFeignHystrixService.get(id);
        return commonResult;
    }


    /**
     * 加了@HystrixCommand注解，必须有对应的处理降级的方法，没有则报错，且该方法的返回值和形参必须与请求方法的一致，否则报错：无法找到降级方法。
     * */
    //这里添加成功也会调用熔断方法，待处理？？
    @PostMapping(value = "/insert")
    @ResponseBody                   //这里的"myFallbackMethod"就是处理降级的方法名
    @HystrixCommand(fallbackMethod = "myFallbackMethod", commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")})
    public CommonResult<PaymentUser> insert(@RequestBody PaymentUser paymentUser){
        CommonResult<PaymentUser> commonResult = paymentFeignHystrixService.insert(paymentUser);
        return commonResult;
    }

    /**
     * 1，服务降级后调用的方法，进行温馨提示
     * 2，服务降级的方法的"返回值和形参列表"必须与请求方法一致，就会方法重载时找不到该方法，或者否则报错：incompatible ..，
     * */
    public CommonResult<PaymentUser> myFallbackMethod(PaymentUser paymentUser){
        return new CommonResult(444,"降级方法与请求形参相同==>服务降级！");
    }
    /**下面的方法虽与@HystrixCommand指定的降级方法名相同，但是不会被调用，因为形参不同*/
    public CommonResult<PaymentUser> myFallbackMethod(String str){
        return new CommonResult(444,"形参不同==>降级！");
    }

    public CommonResult<PaymentUser> getFallBackHandler(Long id){
        return new CommonResult(555,"查询方法，进行降级！");
    }


}
