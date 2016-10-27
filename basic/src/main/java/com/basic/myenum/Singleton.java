package com.basic.myenum;

/**
 * Created by wangzhilong on 2016/10/17.
 */
public enum Singleton {
    INSTANCE;

    public static void main(String[] args) {
        System.out.println(Singleton.INSTANCE.name());
    }
}
