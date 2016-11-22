package com.leader.us.lession6;

/**
 * Created by wangzhilong on 2016/11/17.
 */
public class SampleClass extends SampleBaseClass {

    private final static byte b = 100 ;
    private int i=5 ;
    private long  l = 10 ;
    private Object obj = new Object();
    public SampleClass() {
    }

    public SampleClass(int i, long l) {
        this.i = i;
        this.l = l;
    }

    public static byte getB() {
        return b;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }
}

class SampleBaseClass{
    protected short s = 20 ;
}
