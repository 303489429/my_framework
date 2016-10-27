package com.wzl.sort;

/**
 * Created by wangzhilong on 2016/10/26.
 */
public class PriceOrdering implements Ordering {
    private double value ;
    private int order ;

    public PriceOrdering(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setOrder(int order) {
        this.order = order ;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "PriceOrdering{" +
                "value=" + value +
                ", order=" + order +
                '}';
    }
}
