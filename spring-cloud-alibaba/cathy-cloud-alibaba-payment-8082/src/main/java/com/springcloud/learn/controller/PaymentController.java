package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable(value = "id") Long id){
        PaymentUser paymentUser = paymentService.getUserById(id);
        if(paymentUser != null){
            return new CommonResult<PaymentUser>(200,"success,port:" + serverPort,paymentUser);
        }
        return new CommonResult<>(444,"failure,port" + serverPort,null);
    }

    @PostMapping(value="/insert")
    @ResponseBody
    public CommonResult<PaymentUser> insert(@RequestBody PaymentUser user){
        int count = paymentService.insert(user);
        if(count > 0) {
            return new CommonResult(200,"添加成功，端口：" + serverPort,count);
        }else{
            return new CommonResult(444,"添加失败，端口：" + serverPort,null);
        }
    }





}
