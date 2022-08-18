package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/payment")
@RefreshScope   //动态刷新获取Nacos总配置中心的数据需要加此注解
public class PaymentController {
    /**使用SpringBoot自带的SLF4J*/
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getById(@PathVariable(value = "id") Long id){
        logger.info("configInfo==>" + configInfo);   //打印从总配置中心获取的信息，验证总配置中心的修改操作是否即时更新
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
