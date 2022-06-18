package com.springcloud.learn.service.impl;

import com.springcloud.learn.entity.User;
import com.springcloud.learn.mapper.UserMapper;
import com.springcloud.learn.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource   //这里使用@Resource注解也可以
    UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
