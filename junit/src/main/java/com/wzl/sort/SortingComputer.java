package com.wzl.sort;

import java.util.Collection;

/**
 * Created by wangzhilong on 2016/10/26.
 */
public class SortingComputer<V extends Ordering> {
    private final Collection<V> orderings  ;

    public SortingComputer(Collection<V> orderings) {
        this.orderings = orderings;
    }

    public void computer(){
        double lastValue = Double.MIN_VALUE ;
        int order = 0 ;
        for (V value : orderings){
            if(lastValue != value.getValue()){
                order++;
            }
            value.setOrder(order);
            lastValue = value.getValue();
        }
    }

}
