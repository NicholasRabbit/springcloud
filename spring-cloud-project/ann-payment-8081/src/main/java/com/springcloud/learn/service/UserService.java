package com.springcloud.learn.service;

import com.springcloud.learn.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    //查询
    public abstract User getUserById(Long id);

    //增加
    public abstract int insert(User user);
}
