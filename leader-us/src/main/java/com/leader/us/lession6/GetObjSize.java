package com.leader.us.lession6;

import com.leader.us.tool.UnsafeUtil;
import sun.misc.Unsafe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public class GetObjSize {
    private static Unsafe unsafe = UnsafeUtil.getUnsafe();
    public static void main(String[] args) {
        try {
            final Method arrayIndexScale = Unsafe.class.getMethod("arrayIndexScale",Class.class);
           int p =  ((Number)arrayIndexScale.invoke(unsafe,Object[].class)).intValue();
            System.out.println("p="+p);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
