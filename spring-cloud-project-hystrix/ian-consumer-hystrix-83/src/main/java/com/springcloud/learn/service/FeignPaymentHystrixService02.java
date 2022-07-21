package com.springcloud.learn.service;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.impl.FeignPaymentHystrixService02Impl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 1，消费者侧的Feign，在这里配置Hystrix，进行服务管理，执行降级，熔断，限流等操作
 * 2，在@FeignClient注解里属性“fallback”赋值其实现类，由其实现类中的方法进行服务降级处理，避免了在Controller层加注解，
 *    因为模块中的所有服务都要通过FeignService调用，所以在这里配置其实现类服务降级处理，解决了在Controller层配置降级的耦合度高的问题。
 * 注意：
 * 3，因为一个服务模块只能对应一个Feign，所以这里调用ANN-PAYMENT就会报错，因为FeignPaymentHystrixService已经调用ANN-PAYMENT了
 * */

@Service
@FeignClient(value="GABRIELLE-PAYMENT-HYSTRIX",fallback = FeignPaymentHystrixService02Impl.class)   //由FeignPaymentHystrixServiceImpl实现类进行降级处理
public interface FeignPaymentHystrixService02 {

    @GetMapping(value = "/payment-hystrix/feignError/get/{id}")
    public abstract CommonResult<PaymentUser> getErrorById(@PathVariable("id") Long abc);


    @PostMapping(value = "/payment-hystrix/feignErrorInsert")
    public abstract CommonResult<PaymentUser> feignErrorInsert(PaymentUser paymentUser);
}
