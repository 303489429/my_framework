package com.basic.list;

/**
 * Created by wangzhilong on 2016/9/19.
 */

public class TestString {

    public static final String s1 = "5";
    public static String s2 = "5";

    public static String getS(){
        return "5";
    }
    public static void main(String[] args) {
        String a1 = "12";
        String a2 = "12";
        String a3 = new String("12");
        String a4 = new String("12");
        System.out.println(a1 == a2); //1
        System.out.println(a3 == a4); //2
        System.out.println(a1 == a3); //3
        System.out.println("============");
        //==============================
        String b1 = "34";
        String b2 = "3" + "4";
        String b3 = 3 + "4";
        String b4 = "3" + 4;
        System.out.println(b1 == b2); //4
        System.out.println(b1 == b3); //5
        System.out.println(b1 == b4); //6
        System.out.println("============");
        //==============================
        String c1 = "56";
        String c2 = "5";
        String c3 = "6";
        String c4 = c2 + "6";
        String c5 = c2 + c3;
        final String c6 = "5";
        String c7 = c6 + "6";
        String c8 = s1 + "6";
        String c9 = s2 + "6";
        String c0 = getS() + "6";
        System.out.println(c1 == c4); //7
        System.out.println(c1 == c5); //8
        System.out.println(c1 == c7); //9
        System.out.println(c1 == c8); //10
        System.out.println(c1 == c9); //11
        System.out.println(c1 == c0); //12
    }

}