package com.leader.us.lession6;

import com.leader.us.tool.UnsafeUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * Created by wangzhilong on 2016/11/17.
 */
public class TestLession6 {
    private static Unsafe unsafe ;

    @BeforeClass
    public static void init(){
        unsafe = UnsafeUtil.getUnsafe();
        System.out.println("addressSize="+unsafe.addressSize());
    }

    @Test
    public void testBaseSacle(){
        System.out.println("int base offset:" + unsafe.arrayBaseOffset(int[].class));
        System.out.println("Integer base offset:" + unsafe.arrayBaseOffset(Integer[].class));
        System.out.println("long base offset:" + unsafe.arrayBaseOffset(long[].class));
        System.out.println("Long base offset:" + unsafe.arrayBaseOffset(Long[].class));
        System.out.println("double base offset:" + unsafe.arrayBaseOffset(double[].class));
        System.out.println("Double base offset:" + unsafe.arrayBaseOffset(Double[].class));
    }
}
