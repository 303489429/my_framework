package com.concurrent;

/**
 * Created by wangzhilong on 2016/10/14.
 */
public class LocalContext {

    private static final ThreadLocal<LocalContext> HOLDER = new ThreadLocal<LocalContext>(){
        protected LocalContext initialValue() {
            return new LocalContext();
        }
    };

    private LocalContext(){ }

    private String name ;
    private int sex ;

    public static LocalContext get(){
        return HOLDER.get() ;
    }

    public void set(String name , int sex){
        this.name = name ;
        this.sex= sex;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }
}
