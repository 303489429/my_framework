package com.refactor.abstracts;

/**
 * Created by wangzhilong on 2016/8/31.
 */
public class TestAbstract {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(20*1000);
        ChinaPerson chinaPerson = new ChinaPerson();
        System.out.println("构造完成对象");
        Thread.sleep(10*1000);
    }
}
