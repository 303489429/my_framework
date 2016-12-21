package com.leader.us.unsafe;

import com.leader.us.tool.UnsafeUtil;
import com.sun.management.VMOption;
import sun.management.HotSpotDiagnostic;
import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;

/**
 * Created by wangzhilong on 2016/11/28.
 */
public class UnsafeDemo {
    private final static Unsafe UNSAFE = UnsafeUtil.getUnsafe() ;

    private static class Entry{
        private final static long a ;
        static {
            a = 10 ;
        }
        //public Entry(){
        //    this.a = 12 ;
        //}
    }

    /**
     * 创建实例
     * @throws InstantiationException
     */
    public void createEntryByUnsafe() throws InstantiationException {
        Entry entry = (Entry) UNSAFE.allocateInstance(Entry.class);  //创建对象
         System.out.println(entry.a);
    }

    private static class Guard {
        private int ACCESS_ALLOWED = 1;
        public boolean giveAccess() {
            return 42 == ACCESS_ALLOWED;
        }
    }

    /**
     * 破解代码
     */
    public void crackFn(){
        Guard guard = new Guard();
        try {
            Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED") ;
            long fieldOffSet = UNSAFE.objectFieldOffset(f) ;
            UNSAFE.putLong(guard,fieldOffSet,42);
            System.out.println(guard.giveAccess());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public long getAllocateInstanceValue(){
        A a = null;
        try {
            a = (A)UNSAFE.allocateInstance(A.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return a.a();
    }

    public byte[] getClassContent() throws IOException {
        File file = new File("D:/dev/my_framework/leader-us/target/classes/com/leader/us/unsafe/A.class");
        FileInputStream input = new FileInputStream(file) ;
        byte[] content = new byte[(int)file.length()] ;
        input.read(content);
        input.close();
        return content;
    }

    /**
     * class 文件加密存储，内存解密并加装
     * @throws Exception
     */
    public void loadClass() throws Exception{
        byte[] classContents = getClassContent();  //从文件加载一个类
        Class c = UNSAFE.defineClass(null,classContents,0,classContents.length,ClassLoader.getSystemClassLoader(), null); //通过Unsafe加载字节码
        Object o = c.getMethod("a").invoke(c.newInstance(),null);
        System.out.println("o = "+ o);
        //反射调用带参数的方法
        c.getMethod("print",new Class[]{int.class,int.class}).invoke(c.newInstance(),new Object[]{1,12});
    }

    public void pointCompress(){
        HotSpotDiagnostic hsd = new HotSpotDiagnostic();
        VMOption vmo = hsd.getVMOption("UseCompressedOops");
        System.out.println("name="+vmo.getName()+",value="+vmo.getValue());
    }








}
