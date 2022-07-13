package com.springcloud.learn.service;

import com.springcloud.learn.entity.PaymentUser;

public interface PaymentService {

    //查询
    public abstract PaymentUser getUserById(Long id);

    //增加
    public abstract int insert(PaymentUser paymentUser);
}
