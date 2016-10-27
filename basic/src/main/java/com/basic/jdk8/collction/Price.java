package com.basic.jdk8.collction;

import java.math.BigDecimal;

/**
 * Created by wangzhilong on 2016/10/11.
 */
public class Price {

    private String orgCode ;
    private BigDecimal value ;

    public Price(String orgCode, BigDecimal value) {
        this.orgCode = orgCode;
        this.value = value;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Price{" +
                "orgCode='" + orgCode + '\'' +
                ", value=" + value +
                '}';
    }
}
