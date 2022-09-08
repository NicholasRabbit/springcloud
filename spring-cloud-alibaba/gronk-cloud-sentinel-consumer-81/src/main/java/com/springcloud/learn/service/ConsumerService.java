package com.springcloud.learn.service;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    public CommonResult<PaymentUser> getById(Long id){
        return new CommonResult<>(200,"id==>" + id);  //模拟Service层查询
    }
}
