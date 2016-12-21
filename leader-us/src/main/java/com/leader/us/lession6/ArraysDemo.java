package com.leader.us.lession6;

/**
 * Created by wangzhilong on 2016/11/23.
 */
public class ArraysDemo {

    public static void main(String[] args)
    {
        int[][] myArray=new int[5][];  //5行 不同列
        myArray[0]=new int[5];
        myArray[1]=new int[]{1,2,3,4,5,6,7,8};
        //myArray[5]=new int[]{1};

        try
        {
            System.out.println("[0][8] "+myArray[0][7]);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("[1][8] "+myArray[1][7]);
    }
}
