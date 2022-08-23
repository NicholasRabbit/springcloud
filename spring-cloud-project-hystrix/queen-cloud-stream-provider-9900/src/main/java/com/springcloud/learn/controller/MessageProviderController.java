package com.springcloud.learn.controller;

import com.springcloud.learn.service.IMessageProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 一，Stream的作用
 * 1，开发人员不必关心消息中间件如何使用，只需在相关类加上指定注解，即可实现发送消息，订阅消息的功能；
 * 2，使用Stream需要本机安装RabbitMQ软件，访问下面的接口后可发送消息，登陆RabbitMQ界面可看到相关消息的发送动态；
 * 3，Stream目前只支持RabbitMQ，Kafka这俩消息中间件，也支持两者之间的消息传送。本次的范例，9900(消息生产者，发送者)和9911/9922(消息消费者，接收或订阅者)模块都使用RabbitMQ；
 *
 * 二，消息重复消费的问题
 * 消息消费模块绑定RabbitMQ的监听器以后，Stream会把每个模块当作一个组，自动命名，每个组都会接收消息
 * 如果consumer9922/9933是集群，都是支付模块，每个都接收消息，会造成重复支付的错误，因此只需要一个模块接收消息即可。
 * 解决办法：
 * 把consumer9922/9933分配到一个组里，在application.yml里设置bindings.group=groupA。
 *
 * 三，消息的持久化，再消息消费者端配置，见9922-->application.yml
 * 参数group也起到把消息持久化的作用，即当模块consumer9922中设置此参数后，如果它在意外停机，再启动后可接收9900之前发送的未读消息，不加group则接收不到。
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
