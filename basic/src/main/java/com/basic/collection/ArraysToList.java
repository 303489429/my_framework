package com.basic.collection;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * Created by wangzhilong on 2016/8/30.
 */
public class ArraysToList {

    public static void main(String[] args) {
        System.out.println(Arrays.asList(new String[] {"a","b"}));

        System.out.println(Arrays.asList(new Integer[] {1,2}));

        System.out.println(Arrays.asList(new int[] {2,3}));

        System.out.println(Arrays.asList(new String[] {"a","b"},"c"));

        System.out.println(Arrays.asList(1,2,4));

        System.out.println(Arrays.asList(ArrayUtils.toObject(new int[] {1,6})));

        System.out.println(Arrays.asList(new Object[]{new int[]{2,6}}));

        new MyList(1,2);
    }
}
