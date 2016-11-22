package com.leader.us.lession6;

import cn.loveshisong.sizeof.SizeOf;
import com.leader.us.tool.UnsafeUtil;
import com.sun.glass.ui.Size;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.misc.Unsafe;

/**
 * sizeof 源码地址 https://github.com/rgkjhshi/sizeof.git
 * 编程计算对象的大小
 * JDK7的java.lang.instrument包中有个Instrumentation API提供了getObjectSize方法来计算对象的大小,
 * 这个方法返回的是对象的大小，不包括其成员变量所引用的对象(sun.instrumentInstrumentationImpl实现了该接口)。
 * 而且，这个方法不能直接使用，必须实现一个instrumentation代理类并且打包进JAR文件。
 * Created by wangzhilong on 2016/11/17.
 */
public class TestSampleCalss {

    private static Unsafe unsafe ;

    @BeforeClass
    public static void init(){
        unsafe = UnsafeUtil.getUnsafe();
        System.out.println("addressSize="+unsafe.addressSize());
    }
    @Test
    public void testMain(){

        SampleClass demo = new SampleClass();
        System.out.println(SizeOf.sizeOf(demo));
        System.out.println(SizeOf.fullSizeOf(demo));
        System.out.println(SizeOf.sizeOf(new Object()));
        Assert.assertEquals(12L,SizeOf.sizeOf(new long[0]));

    }


}
