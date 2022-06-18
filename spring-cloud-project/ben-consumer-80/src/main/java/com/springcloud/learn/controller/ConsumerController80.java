package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * 1,从这个模块调用ann-payment-8081支付模块的服务，这里不能再写@Autowired注解，进行service层自动注入了；
 * 2,需要用到RestTemplate进行跨模块调用，类似于HttpClient，也是通过http协议进行数据传输；
 * 3,使用RestTemplate需要在config里进行配置，生成bean;
 * 工程重构
 * 因为有些实体类，工具类，每个模块都要用到，所以把他们都放到commons-api里
 * */
@Controller
@RequestMapping(value="/consumer")
public class ConsumerController80 {

    public static final String PAYMENT_URL = "http://localhost:8081";    //这里暂时先写死模块的调用地址，后期写道配置文件里

    @Resource   //这里使用@Resource注解也可以
    private RestTemplate restTemplate;


    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<User> getUserById(@PathVariable(value="id") String id){
        //一，从这里通过http工具调用ann-payment模块的服务，实现了跨模块调用获取数据
        CommonResult<User> commonResult = restTemplate.getForObject(PAYMENT_URL + "/user/get/" + id, CommonResult.class);  //注意地址拼写
        return commonResult;
    }

    //进行添加数据
    @PostMapping(value="/insert")
    @ResponseBody
    public CommonResult<User> insert(User user){
        //RestTemplate传输的数据格式
        CommonResult insertResult = restTemplate.postForObject(PAYMENT_URL + "/user/insert", user, CommonResult.class);
        return insertResult;
    }
}
