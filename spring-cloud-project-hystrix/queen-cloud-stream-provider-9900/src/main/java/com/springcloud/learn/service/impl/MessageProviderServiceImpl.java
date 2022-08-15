package com.springcloud.learn.service.impl;

import com.springcloud.learn.service.IMessageProviderService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

//@Service    //注意，这里不用写@Service注解了
/**
 * 1，要加此注解，注意Source的包是stream，不要引入错了。
 * 2，这个注解表示把发送消息的类和RabbitMQ的消息管道绑定，开发者并不需要知道具体的消息中间件的操作，
 * SpringCloud Stream组件底层把消息转换成RabbitMQ可以识别的格式并且发送到消息管道了。
 * */
@EnableBinding(Source.class)
public class MessageProviderServiceImpl implements IMessageProviderService {

    @Resource
    MessageChannel output;

    @Override
    public String  sendMessage() {
        String uuid = UUID.randomUUID().toString();
        /**MessageBuilder包不要引用错*/
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("message uuid==>" + uuid);
        return "message uuid==>" + uuid;
    }
}
