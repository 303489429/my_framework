package com.basic.guava;

import com.google.common.collect.Sets;

import java.util.HashSet;

import static com.google.common.collect.Sets.*;

/**
 * Created by wangzhilong on 2016/8/25.
 */
public class SetsTest {

    public void getName(String s, String a){

    }

    public static void main(String[] args) {
        HashSet<Integer> setA = newHashSet( 1 ,2 ,3 ,4 ,5 );
        HashSet<Integer> setB = newHashSet( 4 ,5 ,6 ,7 ,8 );
        Sets.SetView<Integer> union = union(setA, setB); //并集
        System.out.println( "union:" );
        for(Integer integer : union)
            System.out.println(integer);
        Sets.SetView<Integer> difference = difference(setA, setB);  //左边集合特有的元素，差集
        System.out.println( "difference:" );
        for(Integer integer : difference)
            System.out.println(integer);
        Sets.SetView<Integer> intersection = intersection(setA, setB); //交集
        System.out.println( "intersection:" );
        for(Integer integer : intersection)
            System.out.println(integer);

        new SetsTest().getName("3","a");
    }

}
