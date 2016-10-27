package com.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能概要：消息产生，提交到队列中去
 * Created by wangzhilong on 2016/8/29.
 */
@Service("messageProducer")
public class MessageProducer {
    @Autowired
    private AmqpTemplate amqpTemplate ;

    public void sendMessage(Object message){
        System.out.println("to send message : "+message);
        amqpTemplate.convertAndSend("queueTestKey",message);
    }
}
