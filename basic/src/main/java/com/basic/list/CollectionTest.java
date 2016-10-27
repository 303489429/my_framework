package com.basic.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by wangzhilong on 2016/10/13.
 */
public class CollectionTest {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();

        Collection<String> s = Collections.unmodifiableCollection(c);
        s.add("22"); // 这个可以得到一个集合的镜像，它的返回结果不可直接被改变，否则会提示
        c.add("ss");  //为了就是保护数据不要被改变。另外，修改原Collections时，会同时修改对应的镜像。
        System.out.println(s);
    }
}
