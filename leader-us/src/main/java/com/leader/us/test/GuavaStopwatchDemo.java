package com.leader.us.test;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhilong on 2016/11/29.
 */
public class GuavaStopwatchDemo {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch watch = Stopwatch.createStarted();
        Thread.sleep(1002);
        long nanos =  watch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(nanos);
        System.out.println(watch.toString());

        watch.reset().start();
        Thread.sleep(102);

        System.out.println(watch.toString());
    }
}
