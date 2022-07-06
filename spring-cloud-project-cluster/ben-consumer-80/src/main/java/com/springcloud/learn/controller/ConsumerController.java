package com.springcloud.learn.controller;

import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping(value="/consumer")
public class ConsumerController {

    //public static final String PAYMENT_URL = "http://localhost:8081";    //单服务模块访问地址写法
    public static final String PAYMENT_URL = "http://ANN-PAYMENT";   //访问集群时的写法，这个路径是集群中多个模块的设置好的共同的名字，也是注册在Eureka注册中心的名字

    @Resource   //这里使用@Resource注解也可以
    private RestTemplate restTemplate;


    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getUserById(@PathVariable(value="id") String id){
        //一，从这里通过http工具调用ann-payment模块的服务，实现了跨模块调用获取数据；
        //   使用getForObject(..)方法；
        CommonResult<PaymentUser> commonResult = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);  //注意地址拼写
        return commonResult;
    }

    //二，RestTemplate.java的getForEntity(..)方法练习，同时复习Servlet;
    @GetMapping("/getForEntity/{id}")
    public void getForEntity(@PathVariable(value="id") String id, HttpServletRequest request, HttpServletResponse response){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, String.class);
        HttpHeaders headers = responseEntity.getHeaders();
        MediaType contentType = headers.getContentType();
        String responseBody = responseEntity.getBody();   //这里获取responseBody，其数据格式就是json形式的，因此接口如果加@ResponseBody注解，则返回的是json，同理请求参数加注解@RequestBody的用法
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
             writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.print("headers" + headers.toString() + "<br>");
        writer.print("contentType:" + contentType + "<br>");
        writer.print("responseBody:" + responseBody + "<br>");
        writer.print("statusCode:" + statusCode + "<br>");
        writer.print("statusCodeValue:" + statusCodeValue + "<br>");
        //输出流清空，关闭
        writer.flush();
        writer.close();
    }

    //进行添加数据
    @PostMapping(value="/insert")
    @ResponseBody
    public CommonResult<PaymentUser> insert(PaymentUser paymentUser){
        //RestTemplate传输的数据格式，可以为form，或者json
        CommonResult insertResult = restTemplate.postForObject(PAYMENT_URL + "/payment/insert", paymentUser, CommonResult.class);
        return insertResult;
    }

}
