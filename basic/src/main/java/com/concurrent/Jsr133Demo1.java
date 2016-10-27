package com.concurrent;

import sun.misc.Unsafe;

/**
 * Created by wangzhilong on 2016/9/7.
 */
public class Jsr133Demo1 {
    private static  int x =0 ;
    private static  int y = 0  ;

    static class MyTaskOne implements Runnable{
        //@Override
        //public void run() {
        //    int r1 = y ;
        //    if(r1 != 0)
        //        x=1 ;
        //}

        @Override
        public void run() {
            int r1 = x ;
            if(r1 == 1)
                y=1 ;
        }
    }
    static class  MyTaskTwo implements Runnable{
        //@Override
        //public void run() {
        //    int r2 = x ;
        //    if(r2 != 0)
        //        y=2 ;
        //}

        @Override
        public void run() {
            Unsafe.getUnsafe().loadFence();
            int r2 = y ;
            if(r2 == 1)
                x=1 ;
            else
                x=1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int len = 1 ;
        Jsr133Demo1 demo1 = new Jsr133Demo1();
        Thread[] t1 = new Thread[len] ;
        Thread[] t2 = new Thread[len];

        for (int i = 0; i < len; i++) {
            t1[i] = new Thread(new MyTaskOne());
            t2[i]=new Thread(new MyTaskTwo());
        }
        for (int i = 0; i < len ; i++) {
            t1[i].start();
            t2[i].start();
        }
        for (int i = 0; i < len; i++) {
            t1[i].join();
            t2[i].join();
        }
        System.out.println("x="+demo1.x +",y="+demo1.y);
    }
}
