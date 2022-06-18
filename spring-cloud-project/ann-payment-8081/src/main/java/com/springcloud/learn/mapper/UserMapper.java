package com.springcloud.learn.mapper;

import com.springcloud.learn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper  //这里也可用@Repository，建议使用MyBatis的@Mapper注解
public interface UserMapper {

    //查询
    public abstract User getUserById(Long id);

    //增加
    public abstract int insert(User user);

}
