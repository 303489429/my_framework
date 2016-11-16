package com.effective.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangzhilong on 2016/11/15.
 */
public class TestSpeed {

    private static List list ;

    static {
        list = new ArrayList();
        for (int i = 0; i < 50000000; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        long st0 = System.currentTimeMillis();
        for (int i = 0 ,length = list.size(); i < length; i++) {

        }
        long et0 = System.currentTimeMillis();
        System.out.println("time0="+(et0-st0)+"ms");


        long st1 = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {

        }
        long et1 = System.currentTimeMillis();
        System.out.println("time1="+(et1-st1)+"ms");

    }
}
