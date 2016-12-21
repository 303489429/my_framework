package com.leader.us.lession6;

import com.leader.us.tool.UnsafeUtil;
import sun.misc.Unsafe;

/**
 * Created by wangzhilong on 2016/11/15.
 */
public class ArrayTest {

    private static Unsafe unsafe = UnsafeUtil.getUnsafe();

    public static void main(String[] args) {
        System.out.println("addressSize="+unsafe.addressSize());
        System.out.println("int 1 base offset:" + unsafe.arrayBaseOffset(int[].class));
        System.out.println("Integer 1 base offset:" + unsafe.arrayBaseOffset(Integer[].class));
        System.out.println("long 1 base offset:" + unsafe.arrayBaseOffset(long[].class));
        System.out.println("Long 1 base offset:" + unsafe.arrayBaseOffset(Long[].class));
        System.out.println("double 1 base offset:" + unsafe.arrayBaseOffset(double[].class));
        System.out.println("Double 1 base offset:" + unsafe.arrayBaseOffset(Double[].class));
    }

}
