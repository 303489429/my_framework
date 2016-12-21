package com.leader.us.test;

import org.springframework.util.StopWatch;

/**
 * Created by wangzhilong on 2016/11/29.
 */
public class SpringStopwatchDemo {

    public static void main(String[] args) throws InterruptedException {
        StopWatch watch = new StopWatch() ;
        watch.start("A");
        Thread.sleep(100);
        watch.stop();

        watch.start("B");
        Thread.sleep(200);
        watch.stop();

        watch.start("C");
        Thread.sleep(1293);
        watch.stop();

        System.out.println(watch.prettyPrint());
    }
}
