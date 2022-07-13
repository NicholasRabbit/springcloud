package com.springcloud.learn.service;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * OpenFeign范例
 * 1，OpenFeign的作用相当于Ribbon和RestTemplate，是两者的结合；
 * 2，它的使用更便捷了，写在service层，就相当于普通的service一样，更符合日常的使用习惯，但实际是调用另一个模块的服务；
 *    注意它是个接口，不是类
 * 3，OpenFeign调用服务时，是有时间限制的，即有规定的等待时间，默认等待1秒钟，可以在配置文件进行设置；
 * */


@Service
@FeignClient(value="ANN-PAYMENT")   //这里要加@FeignClient注解，value的取值是支付服务模块集群的服务名称
public interface PaymentFeignService {

    //调用接口
    @GetMapping(value = "/payment/get/{id}")  //这里赋值支付模块的接口，当controller调用此方法，就会访问支付模块对应的接口
    public abstract CommonResult<PaymentUser> getById(@PathVariable(value="id") Long id);

    //测试超时
    @GetMapping(value = "/payment/feignTimeout/get/{id}")
    public abstract CommonResult<PaymentUser> getTimeoutById(@PathVariable("id") Long id);
}
