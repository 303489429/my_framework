package com.effective.two;

/**
 * Created by wangzhilong on 2016/11/25.
 */
public class NestedDemo {

    private  Integer a = 1 ;

     class InnerClass{
        private String name ;
        public InnerClass(){
            System.out.println("a="+a);
            System.out.println("静态内部类初始化完成");
            print();
        }
    }
    public void print(){
        InnerClass innerClass = new InnerClass();
    }

    public static void main(String[] args) {
       NestedDemo demo = new NestedDemo();
        demo.print();
    }
}
