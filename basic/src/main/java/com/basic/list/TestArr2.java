package com.basic.list;

/**
 * CPU  CACHA 机制导致了 cache miss
 * Created by wangzhilong on 2016/9/22.
 */
public class TestArr2 {
    public static void main(String[] args) {
        int[][] a = new int[5000][5000] ;
        for (int i = 0; i < 5000; i++) {
            for (int j = 0; j < 5000; j++) {
                a[i][j]=i+j ;
            }
        }
        long t1 = System.currentTimeMillis();
        long sum = 0 ;
        for (int i = 0; i < 5000; i++) {
            for (int j = 0; j < 5000; j++) {
                sum += a[i][j] ;
            }
        }
        long e1 = System.currentTimeMillis() ;
        System.out.println("计算结果："+sum);
        System.out.println("计算耗时："+(e1-t1)+"ms");

        long t2 = System.currentTimeMillis();
        long sum1 = 0 ;
        for (int j = 0; j < 5000; j++) {
            for (int i = 0; i < 5000; i++) {
                sum1 += a[i][j] ;
            }
        }
        long e2 = System.currentTimeMillis() ;
        System.out.println("计算结果："+sum1);
        System.out.println("计算耗时："+(e2-t2)+"ms");
    }
}
