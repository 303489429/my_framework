package com.concurrent;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;

/**
 * Created by wangzhilong on 2016/10/14.
 */
public class TestThreadLocal {

    private void initThread(){
        System.out.println("设置初始值");
        LocalContext.get().set("wzl",18);
    }

    private void print() throws InterruptedException {
        System.out.println("输出………………");
        for (int i = 0; i < 100; i++) {
            if(i < 98)
                Thread.sleep(1);
            else
                System.out.println("i="+i+",thread"+Thread.currentThread().getName());
        }
    }

    private void useThreadLocalContext(){
        System.out.println("姓名："+LocalContext.get().getName());
        System.out.println("年龄："+LocalContext.get().getSex());
    }



    public static void main(String[] args) throws Exception {
        TestThreadLocal local = new TestThreadLocal();
        local.initThread();
        local.print();
        local.useThreadLocalContext();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //LocalContext.get().set("李四",12);
                System.out.println("姓名2："+LocalContext.get().getName());
                System.out.println("年龄2："+LocalContext.get().getSex());
            }
        });
        thread.start();
        thread.join();
        //LocalContext.get().set("王五",8);
        local.useThreadLocalContext();

       ThreadLocalUtil.dumphreadLocals();
    }

}
