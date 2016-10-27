package com.basic.list;

/**
 * Created by wangzhilong on 2016/9/21.
 */
public class TestBigOrLess {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        long sum = 0 ;
        for (int i = 0; i < 100; i++) {
            for (int j = 1; j <= 10000000; j++) {
                sum += j ;
            }
        }
        long e1 = System.currentTimeMillis() ;
        System.out.println("计算结果："+sum);
        System.out.println("计算耗时："+(e1-t1)+"ms");

        long t2 = System.currentTimeMillis();
        long sum1 = 0 ;
        for (int i = 0; i < 100; i++) {
            for (long j = 1; j <= 10000000; j++) {
                sum1 += j ;
            }
        }
        long e2 = System.currentTimeMillis() ;
        System.out.println("计算结果："+sum1);
        System.out.println("计算耗时："+(e2-t2)+"ms");
    }
}
