package com.basic.jdk8.stream;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wangzhilong on 2016/9/30.
 */
public interface IDefaultDemo {
    String getName();

    default int getAge(){
        return 10 ;
    }

    public static <T> List<T> get(int n, T o){
        List list = Lists.newArrayList();
        if(n > 0){
            for (int i = 0; i < n; i++) {
                list.add(o);
            }
        }
        return list;
    }
}
