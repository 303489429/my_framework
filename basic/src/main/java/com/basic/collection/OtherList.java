package com.basic.collection;

/**
 * Created by wangzhilong on 2016/10/31.
 */
public class OtherList extends MyList {

    public OtherList() {
        super(1,2);
    }

    public static void main(String[] args) {
        System.out.println(new OtherList().getAB());
    }
}
