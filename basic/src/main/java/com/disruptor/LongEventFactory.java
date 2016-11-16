package com.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 第二步：事件工厂，定义了如何实例化前面第一步中定义的事件（Event）
 * 需要实现接口EventFactory<T>
 * disruptor 通过EventFactory在RingBuffer中预创建Event实例。
 * 一个Event实例是实际上被作用一个"数据槽"，发布者发布前，先从RingBuffer
 * 获取一个Event的实例，然后往Event实例中填充数据，之后再发布到RingBuffer中，
 * 之后由Consumer获得该Event实例并从中读取数据。
 * Created by wangzhilong on 2016/11/16.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
