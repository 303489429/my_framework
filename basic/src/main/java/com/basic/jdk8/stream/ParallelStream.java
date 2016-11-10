package com.basic.jdk8.stream;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Random;

/**
 * Created by wangzhilong on 2016/10/31.
 */
public class ParallelStream {

    static Collection<Integer> init(){
        Collection<Integer> integers = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            integers.add(random.nextInt());
        }
        return integers ;
    }

    public static void main(String[] args) {
        Collection<Integer> integers = init();
        long st = System.currentTimeMillis() ;
        long count  = integers.stream().filter(i -> i>10000).count() ;
        long et = System.currentTimeMillis();
        System.out.println("串行执行结果："+count+",耗时："+(et-st)+"ms");

        long st2 = System.currentTimeMillis() ;
        long count2  = integers.parallelStream().filter(i -> i>10000).count() ;
        long et2 = System.currentTimeMillis();
        System.out.println("并执行结果："+count2+",耗时："+(et2-st2)+"ms");
    }

}
