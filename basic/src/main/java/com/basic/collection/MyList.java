package com.basic.collection;

/**
 * Created by wangzhilong on 2016/10/31.
 */
public class MyList {
    private  int a;
    private  int b ;

    MyList() {

    }

    MyList(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int getAB(){
        return this.a+this.b;
    }
    @Override
    public String toString() {
        return "MyList{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
