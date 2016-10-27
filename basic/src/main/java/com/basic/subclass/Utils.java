package com.basic.subclass;

/**
 * Created by wangzhilong on 2016/10/17.
 */
public class Utils {
    private Utils(){
        throw new AssertionError();
    }

    public static void main(String[] args) {
        new Utils();
    }
}
