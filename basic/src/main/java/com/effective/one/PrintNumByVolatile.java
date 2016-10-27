package com.effective.one;

/**
 * volatile 实现
 * Created by wangzhilong on 2016/10/26.
 */
public class PrintNumByVolatile {
    private static volatile boolean flag = false ;
    private static  int num = 0 ;

    static class ThreadOne implements Runnable{
        private String threadName ;
        public ThreadOne(String threadName) {
            this.threadName = threadName;
        }
        @Override
        public void run() {
            while (true && num <= 27){
                while (!flag){
                    System.out.println(threadName+":"+(++num));
                    if(num % 3 == 0){
                        flag = true ;
                    }
                }
            }
        }
    }

    static class ThreadTwo implements Runnable{
        private String threadName ;

        public ThreadTwo(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            while (true && num <= 27){
                while (flag){
                    System.out.println(threadName+":"+(++num));
                    if(num % 3 == 0){
                        flag = false ;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new ThreadOne("Thread A"));
        Thread two = new Thread(new ThreadTwo("Thread B"));
        long st = System.currentTimeMillis();
        one.start();
        two.start();
        one.join();
        two.join();
        long ed = System.currentTimeMillis();
        System.out.println("耗时："+(ed-st)+"ms");
    }

}
