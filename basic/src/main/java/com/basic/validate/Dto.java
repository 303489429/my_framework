package com.basic.validate;

import java.math.BigDecimal;

/**
 * Created by wangzhilong on 2016/11/22.
 */
public class Dto {
    private BigDecimal aging ;

    public BigDecimal getAging() {
        return aging;
    }

    public void setAging(BigDecimal aging) {
        this.aging = aging;
    }

    public static void main(String[] args) {
        Dto dto = new Dto();
        dto.setAging(new BigDecimal(0.2));
        boolean hasExist = dto.getAging() == null || 0 == dto.getAging().intValue();
        System.out.println(hasExist);

    }
}
