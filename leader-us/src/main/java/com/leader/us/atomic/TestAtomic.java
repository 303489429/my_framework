package com.leader.us.atomic;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 稍微的测试一下
 * Created by 姜寄羽 on 2016/11/7.
 */
public class TestAtomic {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static TestAtomicInteger testAtomicInteger = new TestAtomicInteger(0);
    private static volatile boolean flag = true;
    private static final int BLOCK = 1024*1024;
    private static final int PART = 200;
    private static int[] table = new int[PART*BLOCK];
    //检验数组中是否有重复数据
    private static void checkTable() {
        Thread[] threads = new Thread[PART];
        final int size = table.length;
        Arrays.setAll(threads,index -> new Thread(() -> {
            for(int i = index*(size/PART) ;i<(index+1)*(size/PART);i++){
                int temp = table[i];
                if (checkOneParam(i, temp)) return;
            }
        }));
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException ignored) {
                ignored.printStackTrace();
            }
        }
        System.out.println("正确运行");
        Arrays.fill(table, 0);
    }
    //检验数组剩下的部分中是否有指定的值
    private static boolean checkOneParam(int i, int temp) {
        for(int j = i+1;j<table.length;j++){
            if(temp == table[j]){
                System.out.println("系统内产生等值");
                Arrays.fill(table, 0);
                return true;
            }
        }
        return false;
    }
    //jdk内部原子类校验
    private static void jdkInt(int index){
        int block = BLOCK;
        int base = index * block;
        int pos = 0;
        while (flag) {
            pos = pos % block;
            table[ base + pos ] = atomicInteger.incrementAndGet();
            pos++;
        }
    }
    //自制原子类校验
    private static void myInt(int index){
        int block = BLOCK;
        int base = index * block;
        int pos = 0;
        while (flag) {
            pos = pos % block;
            table[ base + pos ] = testAtomicInteger.incrementAndGet();
            pos++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //4核启用8个线程
        Thread[] threads = new Thread[PART];
        Arrays.setAll(threads,index -> new Thread(() -> jdkInt(index)));
        Arrays.asList(threads).forEach(Thread::start);
        Thread.sleep(30000);
        flag = false;
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException ignored) {
                ignored.printStackTrace();
            }
        }
        System.out.println("JDK自带类测试完毕，正在检查结果");
        checkTable();
        System.out.println("开始检验更改类");
        flag = true;
        Arrays.setAll(threads,index -> new Thread(() -> myInt(index)));
        Arrays.asList(threads).forEach(Thread::start);
        Thread.sleep(30000);
        flag = false;
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException ignored) {
                ignored.printStackTrace();
            }
        }
        checkTable();
    }
}


