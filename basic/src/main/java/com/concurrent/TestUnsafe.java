package com.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by wangzhilong on 2016/9/8.
 */
public class TestUnsafe {
    private static int a = 5 ;
    private static int b = 0 ;
    private static Unsafe unsafe  ;
    static class MyTaskOne implements Runnable{
        @Override
        public void run() {
            while (true){
                a += 5 ;
            }
        }
    }
    static {
        Field f = null;
        try {
            f =Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static class MyTaskTwo implements Runnable{
        @Override
        public void run() {
            while (true){
                unsafe.loadFence();
                b = a ;
                System.out.println("MyTaskTwo name:"+Thread.currentThread().getName()+",b--------->"+b);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTaskOne());
        Thread t2 = new Thread(new MyTaskTwo());
        t1.start();
        t2.start();
    }
}
