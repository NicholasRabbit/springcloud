package com.springcloud.learn.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 使用Feign要在主启动类加@EnableFeignClients
 * */

@Service
@FeignClient(value="cloud-alibaba-payment")   //这里要加@FeignClient注解，value的取值是支付服务模块集群的服务名称
public interface PaymentFeignService {

    //调用接口
    @SentinelResource("getById")
    @GetMapping(value = "/payment/get/{id}")  //这里赋值支付模块的接口，当controller调用此方法，就会访问支付模块对应的接口
    public abstract CommonResult<PaymentUser> getById(@PathVariable(value="id") Long id);


}
