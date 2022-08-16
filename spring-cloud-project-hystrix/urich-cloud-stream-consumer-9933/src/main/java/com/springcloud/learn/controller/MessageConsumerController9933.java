package com.springcloud.learn.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component     //加@Component注解也可以，因为这里实际已经不是web项目的Controller层了，只是放在controller包下而已
@EnableBinding(Sink.class)
public class MessageConsumerController9933 {

    @Value("@{server.port}")
    private String port;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者9933接收消息==>" + message.getPayload() + ",server port:" + port);
    }

}
