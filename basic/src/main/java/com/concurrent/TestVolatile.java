package com.concurrent;

/**
 * Created by wangzhilong on 2016/9/8.
 */
public class TestVolatile {

    private static int x = 0 ;

    static class MyTaskOne implements Runnable{
        @Override
        public void run() {
            x=1 ;
        }
    }
    static class MyTaskTwo implements Runnable{
        @Override
        public void run() {
            System.out.println("MyTaskTwo name:"+Thread.currentThread().getName()+",b--------->"+x);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            TestVolatile test = new TestVolatile();
            Thread t1 = new Thread(new MyTaskOne());
            Thread t2 = new Thread(new MyTaskTwo());
        }
    }
}
