package com.leader.us.lession6;

import com.leader.us.tool.UnsafeUtil;
import sun.misc.Unsafe;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by wangzhilong on 2016/11/15.
 */
public class MyObj {
    private int a ;
    private byte b ;
    private int c ;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = UnsafeUtil.getUnsafe() ;
        System.out.println("jvm addressSize:"+unsafe.addressSize());   //4或者8代表 32位或者64位JVM系统
        //System.out.println(unsafe.pageSize());

        MyObj myObj = new MyObj();
        long propOffset = unsafe.objectFieldOffset(MyObj.class.getDeclaredField("a")) ;  //获取对象属性的内存地址偏移量
        System.out.println("a offset:"+propOffset);
        unsafe.putInt(myObj,propOffset,6);
        System.out.println("instance getValue:"+myObj.getA()+", unsafe getValue:"+ unsafe.getInt(myObj,propOffset));
    }

}
//32位JVM下
// [HEAD:8bytes] 8
// [a: 4bytes] 12
// [c: 4bytes] 16
// [b: 1bytes] 17
// [padding: 3bytes] 20
// [padding: 4bytes] 24

