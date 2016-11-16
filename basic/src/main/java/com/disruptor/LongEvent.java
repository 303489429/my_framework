package com.disruptor;

/**
 * 第一步：事件(Event)就是通过 Disruptor 进行交换的数据类型。
 * Created by wangzhilong on 2016/11/16.
 */
public class LongEvent {

    private long value ;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }

    public long getValue() {
        return value;
    }
}
