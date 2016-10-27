package com.leader.us.tool;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by wangzhilong on 2016/9/13.
 */
public class UnsafeUtil {
    private static Unsafe unsafe ;
    private UnsafeUtil(){

    }
    static {
        try {
            Field f =Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Unsafe getUnsafe(){
        return unsafe ;
    }
}
