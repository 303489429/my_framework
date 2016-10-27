package com;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangzhilong on 2016/8/29.
 */
//指定bean注入的配置文件
@ContextConfiguration(locations = {"classpath:SpringContext.xml"})
//使用标准的Junit @RunWith注释来告诉Junit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
    protected Logger logger = LoggerFactory.getLogger(getClass()) ;
}
