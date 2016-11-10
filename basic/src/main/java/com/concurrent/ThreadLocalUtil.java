package com.concurrent;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by wangzhilong on 2016/11/10.
 */
public class ThreadLocalUtil {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        dumphreadLocals();
    }

    public static void dumphreadLocals() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Thread thread = Thread.currentThread() ;
        Field threadLocalField = thread.getClass().getDeclaredField("threadLocals");
        threadLocalField.setAccessible(true);
        Object threadLocalTable = threadLocalField.get(thread);

        Class threadLocalMapClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap") ;
        //Class threadLocalMapClass = Class.forName(threadLocalField.getType().getName());
        Field tableField = threadLocalMapClass.getDeclaredField("table") ;
        tableField.setAccessible(true);
        Object[] table = (Object[]) tableField.get(threadLocalTable);

        Class threadLocalMapEntryClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap$Entry");
        Field entryValueField = threadLocalMapEntryClass.getDeclaredField("value") ;
        entryValueField.setAccessible(true);

        //Field referenceField = Reference.class.getDeclaredField("referent") ;
        //referenceField.setAccessible(true);

        for(Object entry : table){
            if(entry != null){
                Object threadLocalValue = entryValueField.get(entry);
                printObject(threadLocalValue);
                //ThreadLocal threadLocal = (ThreadLocal)referenceField.get(entry);
                //System.out.println("thread local  value "+threadLocal);
            }
        }
    }

    static void printObject(Object obj){
        System.out.println("find threadlocal var:"+obj);
        if(obj instanceof Object[]){
            System.out.println("array:"+Arrays.deepToString((Object[]) obj));
        }else if(obj instanceof Reference){
            Reference ref = (Reference) obj ;
            System.out.println("ref: "+ref.getClass().getName()+",ref to "+ref.get());
        }else {
            System.out.println("others: "+obj);
        }
    }
}
