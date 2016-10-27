package com.basic.jdk8.collction;

import java.math.BigDecimal;

/**
 * Created by wangzhilong on 2016/10/11.
 */
public class MergedPrice {

    private String orgCode ;
    private BigDecimal value ;

    public void addValue(BigDecimal value){
        this.value = this.value.add(value);
    }

    public MergedPrice(String orgCode) {
        this.orgCode = orgCode;
        this.value = new BigDecimal(0);
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
}
