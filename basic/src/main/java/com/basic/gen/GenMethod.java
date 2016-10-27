package com.basic.gen;

import java.util.Objects;

/**
 * Created by wangzhilong on 2016/9/14.
 */
public class GenMethod {

    public <T> void printObj(T obj){
        System.out.println(obj);
    }

    public <T extends Annimal> void printObj(T obj){
        obj.eat();
    }

    public static void main(String[] args) {
        GenMethod method = new GenMethod();
        method.printObj(124);
        method.printObj("王治龙");
        //method.printObj(null);

        method.printObj(new Dog());
        System.out.println(null instanceof String);
        System.out.println(null instanceof Objects);

    }
}

