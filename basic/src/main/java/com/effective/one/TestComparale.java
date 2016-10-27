package com.effective.one;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by wangzhilong on 2016/10/26.
 */
public class TestComparale implements Comparable<TestComparale> {

    private String name ;
    private int age ;

    public TestComparale(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(TestComparale o) {
        return compare(this.age,o.age);
    }

    public static int compare(int x , int y){
        return (x < y ) ? -1 : ((x == y ) ? 0 : 1) ;
    }
    @Override
    public String toString() {
        return "TestComparale{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        TestComparale[] arrs ={
                new TestComparale("wzl",18),
                new TestComparale("qqq",12),
                new TestComparale("www",45),
                new TestComparale("zzz",11),
                new TestComparale("lll",19),
        };
        Collections.sort(Arrays.asList(arrs));
        Arrays.sort(arrs);
        for (int i = 0; i < arrs.length; i++) {
            System.out.println("i="+i+","+arrs[i]);
        }

    }
}
