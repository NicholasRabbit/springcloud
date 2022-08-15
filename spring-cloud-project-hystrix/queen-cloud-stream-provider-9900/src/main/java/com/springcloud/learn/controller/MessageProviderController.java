package com.springcloud.learn.controller;

import com.springcloud.learn.service.IMessageProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Stream的作用
 * 1，开发人员不必关心消息中间件的使用，只需在相关类加上指定注解，即可实现发送消息，订阅消息的功能；
 * 2，使用Stream需要本机安装RabbitMQ软件，访问下面的接口后，登陆RabbitMQ界面可看到相关消息的发送动态；
 * 3，Stream目前只支持RabbitMQ，Kafka这俩消息中间件，也支持两者之间的消息传送。本次的范例，9900(消息生产者，发送者)和9911/9922(消息消费者，接收或订阅者)模块都使用RabbitMQ；
 *
 * */

@Controller
@RequestMapping(value = "/stream-provider")
public class MessageProviderController {

    @Resource
    IMessageProviderService messageProviderService;

    @RequestMapping(value = "/sendMessage")
    @ResponseBody
    public String sendMessage(){
        return messageProviderService.sendMessage();
    }

}
