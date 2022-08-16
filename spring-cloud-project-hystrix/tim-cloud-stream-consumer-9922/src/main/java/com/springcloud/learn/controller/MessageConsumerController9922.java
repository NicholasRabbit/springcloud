package com.springcloud.learn.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Consumer9922/9933组成一个集群consumer-receiver，集群内的模块只有一个会接收消息，不会每个重复接收，默认是按轮询算法接收。
 * */

@Component     //加@Component注解也可以，因为这里实际已经不是web项目的Controller层了，只是放在controller包下而已
@EnableBinding(Sink.class)
public class MessageConsumerController9922 {

    @Value("@{server.port}")
    private String port;

    /**
     * 加@StreamListener(Sink.INPUT)表示监听消息队列，INPUT接收消息生产者发送的消息
     * */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者9922接收消息==>" + message.getPayload() + ",server port:" + port);
    }

}
