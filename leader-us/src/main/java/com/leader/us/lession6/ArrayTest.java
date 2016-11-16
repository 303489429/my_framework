package com.leader.us.lession6;

import com.leader.us.tool.UnsafeUtil;
import sun.misc.Unsafe;

/**
 * Created by wangzhilong on 2016/11/15.
 */
public class ArrayTest {

    private static Unsafe unsafe = UnsafeUtil.getUnsafe();

    public static void main(String[] args) {
        System.out.println("int base offset:" + unsafe.arrayBaseOffset(int[].class));
        System.out.println("Integer base offset:" + unsafe.arrayBaseOffset(Integer[].class));
        System.out.println("long base offset:" + unsafe.arrayBaseOffset(long[].class));
        System.out.println("Long base offset:" + unsafe.arrayBaseOffset(Long[].class));
        System.out.println("double base offset:" + unsafe.arrayBaseOffset(double[].class));
        System.out.println("Double base offset:" + unsafe.arrayBaseOffset(Double[].class));
        System.out.println("#######################################");
        long[] longArr = {1L,3L,5L,7L,0L};
        System.out.println("long one vaule:"+ unsafe.getLong(longArr,16L));



    }

}
