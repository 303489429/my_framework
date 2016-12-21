package com.leader.us.unsafe;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by wangzhilong on 2016/11/28.
 */
public class UnsafeDemoTest {

    private static UnsafeDemo unsafeDemo ;

    @BeforeClass
    public static void init(){
        unsafeDemo = new UnsafeDemo();
    }
    @AfterClass
    public static void end(){
        unsafeDemo = null ;
    }

    @Test
    public void testCreateEntryByUnsafe() throws InstantiationException {
        unsafeDemo.createEntryByUnsafe();
    }
    @Test
    public void testCrackFn(){
        unsafeDemo.crackFn();
    }

    @Test
    public void testGetAllocateInstanceValue(){
        Assert.assertEquals(0,unsafeDemo.getAllocateInstanceValue());
    }
    @Test
    public void testLoadClass() throws Exception {
        unsafeDemo.loadClass();
    }
    @Test
    public void testPointCompress(){
        unsafeDemo.pointCompress();
    }


}
