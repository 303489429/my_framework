package com.basic.jdk8.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhilong on 2016/9/30.
 */
public class TestLambda {

    public static void repeatMessage(String text,int count){
        Runnable r = () -> {
            for (int i = 0; i < count; i++) {
                System.out.println(text);
                Thread.yield();
            }
        };

        //new Thread(r).start();

        List<String> matches = new ArrayList<>();
        new Thread(() -> {
            //matches = new ArrayList<>() ; //final 一个有效的final变量被初始化后，就永远不会再被赋予一个新值的变量，
            //matches总是引用同一个ArrayList对象。但是这个对象是可变的，因此线程不安全的。
            if(matches.size() < 100){
                matches.add("hello") ;
            }
        }).start();

        matches.forEach(System.out::println);

        Comparator<Date> comp = (one,two) -> Long.compare(one.getTime(),two.getTime());

        Date d1 = new Date(34);
        Date d2 = new Date(34);
        System.out.println(comp.compare(d1,d2));


    }



    public static void main(String[] args) {
        repeatMessage("hello",1000);
    }

}
