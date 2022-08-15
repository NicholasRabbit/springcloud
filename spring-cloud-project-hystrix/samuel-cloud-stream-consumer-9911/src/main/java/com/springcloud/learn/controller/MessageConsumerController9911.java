package com.springcloud.learn.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 只要消息提供者模块9900发送消息，本消息消费模块由于已经订阅消息，所以会接收到消息，并打印出来。
 * */

@Controller
@EnableBinding(Sink.class)   //注意消费者模块括号内的参数值是Sink.class
@RequestMapping(value = "/stream-consumer")
public class MessageConsumerController9911 {

    @Value("${server.port}")
    private String port;

    /**
     * 加@StreamListener表示监听消息队列，接收消息生产者发送的消息
     * */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){   //注意，形参Message的包不要引用错了
        System.out.println("消费者接收消息==>" + message.getPayload() + ",server port:" + port );
    }

}
