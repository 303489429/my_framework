package com.concurrent;

/**
 * dpuble lock question
 *
 * Created by wangzhilong on 2016/11/25.
 */
public class DoubleLockSingleton {
    private static DoubleLockSingleton instance;

    public static DoubleLockSingleton getInstance() {
        if(instance == null) { //1
            synchronized(DoubleLockSingleton.class) { //2
                if (instance == null){  //3解决问题的管家
                    instance = new DoubleLockSingleton();
                }
            }
        }
        return instance;
    }
    //double lock question
}
