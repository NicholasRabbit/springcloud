package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.User;
import com.springcloud.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//统一加@ResponseBody，老师用的@RestController，报错@ResponseBody

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value="/get/{id}")
    @ResponseBody
    public CommonResult<User> getUserById(@PathVariable(value = "id") Long id){

        User user = userService.getUserById(id);
        if(user != null){
            return new CommonResult<User>(200,"查询成功",user);
        }else{
            return new CommonResult<>(444,"查询失败，无此数据",null);
        }
    }


    @PostMapping(value="/insert")
    @ResponseBody
    public CommonResult<User> insert(@RequestBody User user){   //这里要加@RequestBody注解，Postman的Header要设置为Content-Type:application/json，否则收不到数据
        int count = userService.insert(user);                   //因为RestTemplate默认传输的是json格式的数据（个人推测），也可设置form格式
        if(count > 0) {
            return new CommonResult(200,"添加成功",count);
        }else{
            return new CommonResult(444,"添加失败",null);
        }
    }

}
