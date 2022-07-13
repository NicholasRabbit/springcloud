package com.springcloud.learn.mapper;

import com.springcloud.learn.entity.PaymentUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentHystrixMapper {

    //查询
    public abstract PaymentUser getById(Long id);

    //增加
    public abstract int insert(PaymentUser paymentUser);


}
