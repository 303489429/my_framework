package com.basic.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangzhilong on 2016/11/11.
 */
public class GuavaCacheDemo {

    private static final String KES = "wzl" ;
    private  Cache<String,JsonObject> cache = null ;

    public GuavaCacheDemo(){
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                //.expireAfterAccess(100, TimeUnit.MILLISECONDS) //这个方法是根据某个键值对最后一次访问之后多少时间后移除
                .expireAfterWrite(100,TimeUnit.MILLISECONDS) //这个方法是根据某个键值对被创建或值被替换后多少时间移除
                .build();
    }

    public JsonObject get(String key) throws ExecutionException {
        return this.cache.get(key, () -> {
            System.out.println("初始化数据");
            JsonObject object = new JsonObject();
            object.setName(key);
            object.setDate(new Date());
            return object ;
        }) ;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        GuavaCacheDemo cacheDemo = new GuavaCacheDemo();
        for (int i = 0; i < 10; i++) {
            JsonObject jsonObject = cacheDemo.get(KES+"_"+i);
            if(jsonObject != null ){
                System.out.println(jsonObject);
            }
        }

        System.out.println("######################################");
        for (int i = 0; i < 10; i++) {
            JsonObject jsonObject = cacheDemo.get(KES+"_"+i);
            if(jsonObject != null ){
                System.out.println(jsonObject);
            }
        }

        Thread.sleep(90);
        System.out.println("#################222222222222#####################");
        for (int i = 0; i < 10; i++) {
            JsonObject jsonObject = cacheDemo.get(KES+"_"+i);
            if(jsonObject != null ){
                System.out.println(jsonObject);
            }
        }
    }

}
