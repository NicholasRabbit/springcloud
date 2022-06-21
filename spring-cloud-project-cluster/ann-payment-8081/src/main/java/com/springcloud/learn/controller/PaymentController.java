package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable(value = "id") Long id){
        PaymentUser paymentUser = paymentService.getUserById(id);
        if(paymentUser != null){
            return new CommonResult<PaymentUser>(200,"success",paymentUser);
        }
        return new CommonResult<>(444,"failure",null);
    }

    @PostMapping(value="/insert")
    @ResponseBody
    public CommonResult<PaymentUser> insert(@RequestBody PaymentUser user){   //这里要加@RequestBody注解，Postman的Header要设置为Content-Type:application/json，否则收不到数据
        int count = paymentService.insert(user);                   //因为RestTemplate默认传输的是json格式的数据（个人推测），也可设置form格式
        if(count > 0) {
            return new CommonResult(200,"添加成功",count);
        }else{
            return new CommonResult(444,"添加失败",null);
        }
    }



}
