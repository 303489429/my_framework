package com.leader.us.lession6;

import cn.loveshisong.sizeof.SizeOf;
import com.leader.us.tool.UnsafeUtil;
import sun.misc.Unsafe;

import java.lang.instrument.Instrumentation;

/**
 * Created by wangzhilong on 2016/11/17.
 */
public class SampleClassClient {
    private final static Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    public static void main(String[] args) {
        int addrSize = UNSAFE.addressSize();
        SampleClass sampleClassObject = new SampleClass();
        if(addrSize == 4){
            long addressOfSampleClass = UNSAFE.getInt(sampleClassObject,4L);
            System.out.println("int addressOfSampleClass: "+addressOfSampleClass);
            Class<SampleClass> sampleClassClass = (Class<SampleClass>) UNSAFE.getObject(sampleClassObject,addressOfSampleClass);
            System.out.println("sampleClassClass "+sampleClassClass);
        }else if(addrSize == 8){
            long addressOfSampleClass = UNSAFE.getLong(sampleClassObject,8L);
            System.out.println("long addressOfSampleClass: "+addressOfSampleClass);
            Class<SampleClass> sampleClassClass = (Class<SampleClass>) UNSAFE.getObject(sampleClassObject,addressOfSampleClass);
            System.out.println("sampleClassClass "+sampleClassClass);
        }


    }

}
