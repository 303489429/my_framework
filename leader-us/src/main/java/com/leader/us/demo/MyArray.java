package com.leader.us.demo;

import com.leader.us.tool.UnsafeUtil;

import java.util.Random;

/**
 * Created by wangzhilong on 2016/9/13.
 */
public class MyArray {
    private static int curPos = -1 ;
    private static byte[] data = new byte[11] ;

    public static void getData(int threadNum){
        System.out.println();
        System.out.println("随机线程"+threadNum+"读：");
        UnsafeUtil.getUnsafe().loadFence();
        for (int i = 0; i < MyArray.curPos; i++) {
            System.out.print(" "+MyArray.data[i]);
        }
        System.out.println();
    }

    public static void setData(byte b , int pos){
        MyArray.data[pos] = b ;
        UnsafeUtil.getUnsafe().fullFence();
        System.out.println(" 修改了"+pos+":"+b);
    }

    public static void appendData(byte b){
        UnsafeUtil.getUnsafe().loadFence();
        MyArray.data[MyArray.curPos+1]=b;
        MyArray.curPos++ ;
        UnsafeUtil.getUnsafe().fullFence();
        System.out.println("添加了"+curPos+":"+b);
    }

    public static int getCurPos(){
        UnsafeUtil.getUnsafe().loadFence();
        return curPos;
    }

    public static void setCurPos(int curPos){
        MyArray.curPos = curPos ;
        UnsafeUtil.getUnsafe().fullFence();
    }

    public static void main(String[] args) {
        final int THREADNUM = 10 ;
        Random random = new Random();
        appendData((byte) random.nextInt());
        new Thread(){
            public void run(){
                while (true){
                    if(curPos < 10){
                        appendData((byte)random.nextInt());
                    }
                    setData((byte) random.nextInt(),random.nextInt(curPos));
                }
            }
        }.start();

        for (int i = 0; i < THREADNUM; i++) {
            new Thread(Integer.toString(i)){
                public void run(){
                    while (true){
                        getData(Integer.parseInt(getName()));
                    }
                }
            }.start();
        }

    }
}
