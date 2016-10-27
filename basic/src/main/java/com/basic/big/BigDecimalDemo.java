package com.basic.big;

import java.math.BigDecimal;

/**
 * Created by wangzhilong on 2016/10/8.
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        BigDecimal value = new BigDecimal(4);
        BigDecimal temp = value.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP) ;
        System.out.println(temp);

        System.out.println(value.multiply(temp));
        BigDecimal resRange = new BigDecimal(0.2);
        int range = resRange.setScale(2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        System.out.println("rang="+range);
    }
}
