package com.springcloud.learn.service.impl;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.FeignPaymentHystrixService02;
import org.springframework.stereotype.Component;

/**
 * 1,此类实现FeignPaymentHystrixService，来处理其调用服务时出现的降级问题
 * 2,即使Controller层不使用@DefaultProperties或@HystrixCommand注解，当Feign调用的服务出问题时，依然会调用这里的方法进行降级。
 * */

@Component  //别忘了加此注解，让Spring容器扫描进去
public class FeignPaymentHystrixService02Impl implements FeignPaymentHystrixService02 {
    @Override
    public CommonResult<PaymentUser> getErrorById(Long abc) {
        return new CommonResult<PaymentUser>(444,"FeignPaymentHystrixService02实现类==> get(..)处理降级方法执行！");
    }

    @Override
    public CommonResult<PaymentUser> feignErrorInsert(PaymentUser paymentUser) {
        return new CommonResult<PaymentUser>(444,"FeignPaymentHystrixService02实现类==> insert(..)处理降级方法执行！");
    }
}
