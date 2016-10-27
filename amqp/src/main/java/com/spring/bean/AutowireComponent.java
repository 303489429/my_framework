package com.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * Created by wangzhilong on 2016/10/8.
 */
public abstract class AutowireComponent {
    @Autowired
    private AutowireCapableBeanFactory beanFactory ;

    public <B> B autowireBean(B b){
        this.beanFactory.autowireBean(b);
        this.beanFactory.initializeBean(b, b.toString()) ;
        return b ;
    }

    protected <B> B getBean(Class<B> beanClass){
        return this.beanFactory.getBean(beanClass);
    }

    protected <B> B getBean(String beanName, Class<B> beanClass){
        return this.beanFactory.getBean(beanName,beanClass);
    }

}
