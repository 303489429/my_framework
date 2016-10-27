package com.wzl.basic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangzhilong on 2016/9/9.
 */
public class Jmm {
    private int n1 = 1 ;
    private volatile int n2 = 1 ;

    @Test
    public void testLocalVar1(){
        int ln = n1 ;
        ln = 2 ;
        assertEquals(n1,2);
    }

    @Test
    public void testVolatileVar1(){
        int ln = n2 ;
        ln = 2 ;
        assertEquals(n2,2);
    }

    private Integer a1 = 1 ;
    private volatile Integer a2 = 1 ;

    @Test
    public void testLocalVar2(){
        Integer ln = a1 ;
        ln = 2 ;
        assertEquals(a1,new Integer(2));
    }

    @Test
    public void testVolatileVar2(){
        Integer ln = a2 ;
        ln = 2 ;
        assertEquals(a2,new Integer(2));
    }
}
