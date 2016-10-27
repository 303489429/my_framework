package com.leader.us.jmm;

/**
 * Created by wangzhilong on 2016/9/13.
 */
public class SyncTest {
    volatile int value = 0 ;

    public static synchronized void testSyncStaticOne() throws InterruptedException {
        System.out.println("testSyncStaticOne");
        Thread.sleep(5000);
    }

    public static void testSyncSttaticTwo() throws InterruptedException {
        synchronized (SyncTest.class){
            System.out.println("testSyncSttaticTwo");
            Thread.sleep(5000);
        }
    }

    public synchronized void testSyncOne() throws InterruptedException {
        System.out.println("testSyncOne");
        value ++ ;
        Thread.sleep(2000);
    }
    public void testSyncTwo() throws InterruptedException {
        synchronized (this){
            System.out.println("testSyncTwo");
            Thread.sleep(2000);
        }
    }

    public int getValue(){
        return value ;
    }

    public static  void case1() throws InterruptedException {
        final SyncTest t1 = new SyncTest();
        final SyncTest t2 = new SyncTest();
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //t1.testSyncOne();
                    t1.testSyncSttaticTwo();
                    //SyncTest.testSyncSttaticTwo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //t2.testSyncOne();
                    t2.testSyncStaticOne();
                    System.out.println("value="+t1.getValue());
                    //SyncTest.testSyncStaticOne();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th1.start();
        th2.start();
        th1.join();
        th2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        long st = System.currentTimeMillis();
        case1();
        long et = System.currentTimeMillis();
        System.out.println("time cost:"+(et - st)+"ms");
    }

}
