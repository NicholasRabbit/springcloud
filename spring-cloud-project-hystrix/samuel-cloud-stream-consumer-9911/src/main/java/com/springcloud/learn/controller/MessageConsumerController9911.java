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

@Controller     //加@Component注解也可以，因为这里实际已经不是web项目的Controller层了，只是放在controller包下而已
@EnableBinding(Sink.class)   //注意消费者模块括号内的参数值是Sink.class
//@RequestMapping(value = "/stream-consumer")   //不用加@RequestMapping，因为加了@StreamListener即可监听并接收消息
public class MessageConsumerController9911 {

    @Value("${server.port}")
    private String port;

    /**
     * 加@StreamListener(Sink.INPUT)表示监听消息队列，INPUT接收消息生产者发送的消息
     * */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){   //注意，形参Message的包不要引用错了
        System.out.println("消费者9911接收消息==>" + message.getPayload() + ",server port:" + port );
    }

}
