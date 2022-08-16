package com.springcloud.learn.service.impl;

import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.mapper.PaymentMapper;
import com.springcloud.learn.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service  //只有普通类才可加@Sevice，@Component等表示类的注解，接口interface:PaymentService不可以
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentMapper paymentMapper;

    @Override
    public PaymentUser getUserById(Long id) {
        return paymentMapper.getById(id);
    }


}
