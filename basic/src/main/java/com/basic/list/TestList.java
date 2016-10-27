package com.basic.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhilong on 2016/8/26.
 */
public class TestList {
    public static void main(String[] args) {
        List l = new ArrayList<Number>();
        List<String> ls = l; // unchecked warning
        l.add(0, new Integer(42)); // another unchecked warning
        String s = ls.get(0); // ClassCastException is thrown
        System.out.println(s);
    }
}
