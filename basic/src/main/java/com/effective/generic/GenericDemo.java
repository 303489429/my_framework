package com.effective.generic;

import com.effective.two.Sub;
import com.effective.two.Super;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangzhilong on 2016/11/25.
 */
public class GenericDemo {
    private Set<Integer> integers = new HashSet();
    //private List<Super> supers = new HashSet<Sub>(); error
    //private Super[] supers2 = new Sub[2];   right
    //List<String>[] lists = new List<String>[1];
    List<Integer> integerList = Arrays.asList(42);
    public static void main(String[] args) {

    }
}
