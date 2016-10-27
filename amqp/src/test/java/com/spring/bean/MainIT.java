package com.spring.bean;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangzhilong on 2016/10/8.
 */
public class MainIT {
    @Autowired
    private Main main ;
    private ApplicationContext context ;

    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("classpath:SpringContext.xml");
        main = context.getBean(Main.class) ;
    }

    @Test
    public void testAutowireBean(){
        main.autowireBean();
        main.mualBean();
    }

}
