package com.springcloud.learn.service;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;

public interface PaymentHystrixService {

    //查询
    public abstract PaymentUser getUserById(Long id);

    public abstract PaymentUser getTimeoutById(Long id);

    public abstract PaymentUser getTimeoutFallbackById(Long id);

}
