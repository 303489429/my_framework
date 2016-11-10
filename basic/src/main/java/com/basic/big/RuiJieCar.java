package com.basic.big;

import com.basic.collection.MyList;

/**
 * Created by wangzhilong on 2016/10/27.
 */
public class RuiJieCar {
    public static void main(String[] args) {
        FuTeCar fuTeCar = new FuTeCar();
        fuTeCar.b = "锐界" ;
        System.out.println(fuTeCar.getB());

        //new MyList(2,3); //包级私有的
    }
}
