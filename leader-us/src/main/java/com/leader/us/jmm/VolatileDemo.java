package com.leader.us.jmm;

/**
 * Created by wangzhilong on 2016/9/13.
 */
public class VolatileDemo {
    int a = 0 ;
    volatile boolean f = false ;

    public void write(){
        a = 1 ;
        f = true ;
    }

    public void read(){
        if(f){
            int i = a ;
            System.out.println("i===="+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();
        Thread t2 = new Thread(){
            public void run(){
                demo.read();
            }
        };
        Thread t1 = new Thread(){
            public void run(){
                demo.write();
            }
        };
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(demo.a);
    }

}
