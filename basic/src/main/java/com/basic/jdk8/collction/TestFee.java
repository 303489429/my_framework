package com.basic.jdk8.collction;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * Created by wangzhilong on 2016/10/11.
 */
public class TestFee {
    public static void main(String[] args) {
        Fee fee = new NewFee(1,"wzl",2,new BigDecimal(22)) ;
        System.out.println(JSON.toJSONString(fee));
    }
}
