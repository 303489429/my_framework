package com.refactor.abstracts;

/**
 * Created by wangzhilong on 2016/8/31.
 */
public class ChinaPerson extends AbstractPerson {
    @Override
    public void getName() {
        System.out.println("我是实现方法");
    }
}
