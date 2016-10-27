package com.com.wzl.project;

/**
 * Created by wangzhilong on 2016/9/12.
 */
public class TestRedis {

    public static void main(String[] args) {
        String key = "tender.config" ;
        RedisUtil.set(key,"889900");
        System.out.println(RedisUtil.get(key));
    }
}
