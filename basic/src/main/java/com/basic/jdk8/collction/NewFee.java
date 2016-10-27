package com.basic.jdk8.collction;

import com.alibaba.fastjson.JSON;
import javafx.scene.shape.VLineTo;

import java.math.BigDecimal;

/**
 * Created by wangzhilong on 2016/10/11.
 */
public class NewFee extends Fee{

    private int order ;

    private BigDecimal value ;

    public NewFee(int id, String name, int order,BigDecimal value) {
        super(id, name);
        this.order = order;
        this.value= value ;
    }

    public void addValue(BigDecimal value){
        this.value=this.value.add(value);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public static void main(String[] args) {
        NewFee fee = new NewFee(1,"www",1,new BigDecimal(10));
        System.out.println(JSON.toJSONString(fee));
        fee.addValue(new BigDecimal(2));
        System.out.println(JSON.toJSONString(fee));
    }
}
