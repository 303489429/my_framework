package com.basic.jmm;

/**
 * Created by wangzhilong on 2016/8/30.
 */
public class TestJMM {
    private static int a = 0;

    static class MyTask implements Runnable {
        @Override
        public void run() {
            a = a + 1 ;
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask()) ;
        Thread t2 = new Thread(new MyTask());
        t1.start();
        t2.start();
        System.out.println(a);
        long l = System.currentTimeMillis();
    }
}
