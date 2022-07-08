package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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
    public CommonResult<PaymentUser> insert(@RequestBody PaymentUser user){   //这里要加@RequestBody注解，Postman的Header要设置为Content-Type:application/json，否则收不到数据
        int count = paymentService.insert(user);                   //因为RestTemplate默认传输的是json格式的数据（个人推测），也可设置form格式
        if(count > 0) {
            return new CommonResult(200,"添加成功，端口：" + serverPort,count);
        }else{
            return new CommonResult(444,"添加失败，端口：" + serverPort,null);
        }
    }

    //Feign模块调用超时测试，设置等待3秒
    @GetMapping("/feignTimeout/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getTimeoutById(@PathVariable(value = "id") Long id){
        //待用concurrent包的方法，与Thread.sleep(..)方法相同，模拟等待4秒，超过Feign的时间限制
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PaymentUser paymentUser = paymentService.getUserById(id);
        if(paymentUser != null){
            return new CommonResult<PaymentUser>(200,"success,port:" + serverPort,paymentUser);
        }
        return new CommonResult<>(444,"failure,port" + serverPort,null);
    }



}
