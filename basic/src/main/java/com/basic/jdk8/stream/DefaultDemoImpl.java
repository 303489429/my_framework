package com.basic.jdk8.stream;

/**
 * Created by wangzhilong on 2016/9/30.
 */
public class DefaultDemoImpl implements IDefaultDemo {
    @Override
    public String getName() {
        return "一诺";
    }

    public static void main(String[] args) {
        DefaultDemoImpl impl = new DefaultDemoImpl();
        System.out.println(impl.getName());
        System.out.println(impl.getAge());
        IDefaultDemo.get(2,"一诺").forEach(System.out::println);
    }
}

