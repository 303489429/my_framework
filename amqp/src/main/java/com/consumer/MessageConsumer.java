package com.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 功能概述：消费接收
 * Created by wangzhilong on 2016/8/24.
 */
public class MessageConsumer implements MessageListener{

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Override
    public void onMessage(Message message) {
        logger.info("receive message:{}",message);
    }
}
