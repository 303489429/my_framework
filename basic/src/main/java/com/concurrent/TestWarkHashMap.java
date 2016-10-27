package com.concurrent;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by wangzhilong on 2016/10/17.
 */
public class TestWarkHashMap {
    public static void main(String[] args) throws InterruptedException {
        Integer a = 3 ;
        Map<Integer,String> caches = new WeakHashMap<>();
        caches.put(1,"wzl");
        caches.put(2,"lsizi") ;
        caches.put(a,"走廊");
        for (Integer id : caches.keySet()){
            System.out.println("id x :"+caches.get(id));
        }
        a= null ;

        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.runFinalization();
        System.runFinalizersOnExit(true);//臭名昭著的方法
        Thread.sleep(10000);
        System.out.println("------------------------------");
        for (Integer id : caches.keySet()){
            System.out.println("id y :"+caches.get(id));
        }
    }
}
