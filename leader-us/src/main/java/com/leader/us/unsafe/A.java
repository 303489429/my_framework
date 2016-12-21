package com.leader.us.unsafe;

/**
 * Created by wangzhilong on 2016/11/28.
 */
public class A{
    private long a ;
    public A(){
        this.a = 2 ;
    }
    public long a(){
        return this.a ;
    }
    public void print(int i,int j){
        System.out.println("a+b="+(i+j));
    }
}
