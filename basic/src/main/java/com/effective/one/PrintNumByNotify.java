package com.effective.one;

/**
 * 通知阻塞，代码简洁 有锁
 * Created by wangzhilong on 2016/10/26.
 */
public class PrintNumByNotify {

    public static void main(String[] args) throws InterruptedException {
        int num =2 ;
        Thread[] threads = new Thread[num] ;
        final Business business= new Business();
        for (int i = 0; i < num; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        business.printNum(Thread.currentThread().getName());
                    }
                }
            },i+"") ;
            threads[i] = t ;
        }
        long st = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }
        long ed = System.currentTimeMillis();
        System.out.println("耗时："+(ed-st)+"ms");
    }

    static class Business{
        private int number = 0 ;
        private int state = 1 ;

        public synchronized void printNum(String name){
            int num = Integer.parseInt(name) + 1 ;
            while(state != num){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 3; i++) {
                number++ ;
                System.out.println("线程"+num+"->"+number);
            }
            state = state % 2 + 1 ;
            this.notifyAll();
        }
    }

}
