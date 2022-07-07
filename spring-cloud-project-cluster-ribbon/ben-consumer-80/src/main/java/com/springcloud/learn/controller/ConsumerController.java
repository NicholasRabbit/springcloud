package com.springcloud.learn.controller;

import com.netflix.appinfo.InstanceInfo;
import com.springcloud.learn.entity.CommonResult;
import com.springcloud.learn.entity.PaymentUser;
import com.springcloud.learn.myLoadBalancer.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value="/consumer")
public class ConsumerController {

    //public static final String PAYMENT_URL = "http://localhost:8081";    //单服务模块访问地址写法
    public static final String PAYMENT_URL = "http://ANN-PAYMENT";   //访问集群时的写法，这个路径是集群中多个模块的设置好的共同的名字，也是注册在Eureka注册中心的名字

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;  //注意这个类是SpringCloud的类，不是netflix的，不要引入错了
    @Autowired
    private LoadBalancer myLoadBalancer;


    @GetMapping("/get/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getUserById(@PathVariable(value="id") String id){
        //从这里通过http工具调用ann-payment模块的服务，实现了跨模块调用获取数据；
        //   使用getForObject(..)方法；
        CommonResult<PaymentUser> commonResult = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);  //注意地址拼写
        return commonResult;
    }




    @PostMapping(value="/insert")
    @ResponseBody
    public CommonResult<PaymentUser> insert(@RequestBody PaymentUser paymentUser){
        //RestTemplate传输的数据格式，可以为form，或者json，使用postForObject(..)方法
        CommonResult<PaymentUser> insertResult = restTemplate.postForObject(PAYMENT_URL + "/payment/insert", paymentUser, CommonResult.class);
        return insertResult;
    }

    /**
     * 一，使用自定义的轮询算法来访问服务
     * 注意，测试使用自己的轮询算法时，要把ApplicationContextConfig.java里的@LoadBalanced取消
     *      主启动类的@RibbonClient注解也要取消。
     * */
    @GetMapping("/getMyLB/{id}")
    @ResponseBody
    public CommonResult<PaymentUser> getMyLB(@PathVariable(value="id") String id){
        //1,首先使用服务发现类，来找到服务
        List<ServiceInstance> instances = discoveryClient.getInstances("ANN-PAYMENT");
        if(instances == null || instances.size() == 0){
            return null;
        }
        ServiceInstance serviceInstance = myLoadBalancer.getServiceInstance(instances);
        URI uri = serviceInstance.getUri();

        CommonResult<PaymentUser> commonResult = restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
        return commonResult;
    }






}
