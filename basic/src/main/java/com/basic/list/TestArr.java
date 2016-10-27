package com.basic.list;

/**
 * Created by wangzhilong on 2016/9/22.
 */
public class TestArr {

    public static void main(String[] args) {
        int[] arr = {1,2,4,6};
        long t1 = System.currentTimeMillis();
        long sum = 0 ;
        for (int i = 0; i < 10000000; i++) {
            sum += arr[0] + arr[1] ;
        }
        long e1 = System.currentTimeMillis() ;
        System.out.println("计算结果："+sum);
        System.out.println("计算耗时："+(e1-t1)+"ms");

        long t2 = System.currentTimeMillis();
        long sum1 = 0 ;
        int a = arr[0];
        int b = arr[1] ;
        for (int i = 0; i < 10000000; i++) {
            sum1 += a+ b;
        }
        long e2 = System.currentTimeMillis() ;
        System.out.println("计算结果："+sum1);
        System.out.println("计算耗时："+(e2-t2)+"ms");
    }

}
