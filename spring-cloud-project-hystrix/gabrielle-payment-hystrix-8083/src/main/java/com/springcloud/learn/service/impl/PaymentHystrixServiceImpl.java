package com.springcloud.learn.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.mapper.PaymentHystrixMapper;

import com.springcloud.learn.service.PaymentHystrixService;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForYear;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Resource
    PaymentHystrixMapper paymentHystrixMapper;

    @Override
    public PaymentUser getUserById(Long id) {
        return paymentHystrixMapper.getById(id);
    }

    @Override
    public PaymentUser getTimeoutById(Long id) {
        //设置睡眠3秒，模拟延时，这里不使用Hystrix降级处理，为了做对比
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentHystrixMapper.getById(id);
    }

    /**
     *延时，或出错，触发Hystrix降级
     * 1, fallbackMethod = "fallBackHandler"，配置降级后，向调用方返回一个处理方法，即备用方案
     * 2, commandProperties ： 配置的时设定的允许最大时间，超过则进行降级
     * 3, 服务降级的目的是避免单个调用错误，影响其它服务，造成连锁反应，致使服务阻塞宕机；
     */
    @HystrixCommand(fallbackMethod = "fallBackHandler", commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")}) //单位毫秒
    public PaymentUser getTimeoutFallbackById(Long id) {
        //设置睡眠3秒，模拟延时
        try {
            TimeUnit.MILLISECONDS.sleep(3000);  //注意不要写成MICROSECONDS，这是百万分之一秒……
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentHystrixMapper.getById(id);
    }

    /**
     * 1，降级后调用的处理方法，在controller层添加提示语句
     * 2，注意降级方法的返回值，形参列表必须与请求的方法一致，否则报错
     * */
    public PaymentUser fallBackHandler(Long id){
        PaymentUser user = new PaymentUser();
        user.setId(-1L);  //这里为了确认执行此方法，把id设为-1，方便controller做区分，因为数据库查不到也是返回null,因此不能return null
        //return null;
        return user;
    }

}
