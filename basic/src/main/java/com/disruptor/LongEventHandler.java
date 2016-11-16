package com.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 第三步：定义事件处理的具体实现
 * Created by wangzhilong on 2016/11/16.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence , boolean endOfBatch) throws Exception {
        System.out.println("Event:"+event+",sequence:"+sequence+",endOfBatch:"+endOfBatch);
    }
}
