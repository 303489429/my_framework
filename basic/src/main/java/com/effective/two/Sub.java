package com.effective.two;

import java.util.Date;

/**
 * 因为子类的构造函数里面必然调用了父类的构造函数
 * 一般来说，子类的构造函数里面会隐式的调用父类的构造函数，就是那个super.什么什么的
 * 因为隐式调用，所以看不到。但是是存在的。如果你显式的调用那么必须放在第一行。
 * 而且从理论上来说，既然你要初始化子类，子类会继承父类的一部分属性和方法，如果你不初始化父类，那么子类岂不是没有意义。继承不了父类的属性和方法。
 * 所以，初始化子类必须要初始化父类
 * Created by wangzhilong on 2016/11/25.
 */
public class Sub extends Super {
    private final Date date ;
    String s = "";
    public Sub(){
        //super();  //隐式调用父类空，显示第一行
        this.date = new Date() ; //初始化前先初始化父类
    }

    public void overriedMe(){
        System.out.println("sub");
        System.out.println(date);
    }

    public static void main(String[] args) {
        Super sub = new Sub();
        sub.overriedMe();

    }
}
