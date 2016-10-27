package com.basic.gen;

/**
 * Created by wangzhilong on 2016/9/14.
 */
public class Main {
    public void test(Object o) {
        System.out.println("Object");
    }
    public void test(Integer s) {
        System.out.println("Integer");
    }
    public void test(String s) {
        System.out.println("String");
    }
    public void test(float f) {
        System.out.println("float");
    }
    public void test(double d) {
        System.out.println("double");
    }
    public void test(long l) {
        System.out.println("long");
    }
    public void test(char c) {
        System.out.println("char");
    }
    public static void main(String[] args) {
        Main that = new Main();
        that.test(0);
        that.test(0.0);
        //that.test(null);
    }
}
