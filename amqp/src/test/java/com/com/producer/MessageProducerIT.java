package com.com.producer;

import com.producer.MessageProducer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangzhilong on 2016/8/29.
 */
public class MessageProducerIT {

    private static ApplicationContext context ;

    @BeforeClass
    public static void setUp(){
        context = new ClassPathXmlApplicationContext("classpath:SpringContext.xml");
    }

    @Test
    public void testSendMessage(){
        MessageProducer messageProducer = (MessageProducer) context.getBean("messageProducer");
        int a = 1 ;
        while(a > 0){
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            //messageProducer.sendMessage("Hello, I am amqp ssender num :" + a--);
            //try {
            //    //暂停一下好让消息消费者去取消打印出来
            //    Thread.sleep(1000);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        }
        while (true){

        }
    }
}
