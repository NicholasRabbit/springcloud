package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/payment-hystrix")
public class PaymentHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;
    @Value(value = "${server.port}")
    String serverPort;


    /**
     * 正常调用
     * */
    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable(value = "id") Long id){
        PaymentUser paymentUser = paymentHystrixService.getUserById(id);
        if(paymentUser != null){
            return new CommonResult<PaymentUser>(200,"success,port:" + serverPort,paymentUser);
        }
        //service层Hystrix处理服务降级后，返回的是null，执行以下一句，因此再这里添加提示语句
        return new CommonResult<PaymentUser>(444,"ailure,port" + serverPort,null);
    }


    /**
     * 模拟延时，不做降级处理，做对比
     * */
    @GetMapping("/getTimeoutById/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getTimeoutById(@PathVariable(value = "id") Long id){
        PaymentUser paymentUser = paymentHystrixService.getTimeoutById(id);
        if(paymentUser != null){
            return new CommonResult<PaymentUser>(200,"success,port:" + serverPort,paymentUser);
        }
        return new CommonResult<PaymentUser>(444,"failure,port" + serverPort,null);
    }

    /**
     * 在service层使用Hystrix做降级处理
     * */
    @GetMapping("/getTimeoutFallbackById/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getTimeoutFallbackById(@PathVariable(value = "id") Long id){
        PaymentUser paymentUser = paymentHystrixService.getTimeoutFallbackById(id);
        if(paymentUser != null && paymentUser.getId() > 0){
            return new CommonResult<PaymentUser>(200,"success,port:" + serverPort,paymentUser);
        }else if(paymentUser.getId() == -1){  //Hystrix进行降级后，调用处理方法，把id赋值为-1
            //service层Hystrix处理服务降级后，返回的是null，执行以下一句，因此再这里添加提示语句
            return new CommonResult<PaymentUser>(444,"Hystrix服务降级启动，请稍后再试，failure,port" + serverPort,null);
        }

        return new CommonResult<PaymentUser>(444,"failure,port" + serverPort,null);
    }


    /**consumer83 模块Feign降级调用出错接口 : 1*/
    @GetMapping("/feignError/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getErrorById(@PathVariable(value = "id") Long id){
        int i = 10/0;  //抛出运行时异常，服务出错，触发调用者的降级
        PaymentUser paymentUser = paymentHystrixService.getUserById(id);
        if(paymentUser != null){
            return new CommonResult<PaymentUser>(200,"success,port:" + serverPort,paymentUser);
        }
        return new CommonResult<>(444,"failure,port" + serverPort,null);
    }

    /**Feign降级调用出错接口 : 2*/
    @PostMapping(value="/feignErrorInsert")
    @ResponseBody
    public CommonResult<PaymentUser> feignErrorInsert(@RequestBody PaymentUser user){
        int i = 10/0;  //抛出运行时异常，服务出错，触发调用者的降级
        return  new CommonResult<>(000,"测试");
    }


}
