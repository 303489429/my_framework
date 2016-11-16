package com.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public class DisruptorMain {

    private ExecutorService executor = Executors.newCachedThreadPool();
    private final WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
    private final WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
    private final WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();

    private void startDisruptor() {
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        int ringBufferSize = 1024 * 1024; // RingBuffer大小，必须是2的N次方
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,
                ringBufferSize,
                threadFactory,
                ProducerType.SINGLE,
                YIELDING_WAIT);
        EventHandler<LongEvent> eventHandler = new LongEventHandler();
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();
        //发布事件 Disruptor发布过程是一个两阶段提交的过程
        //第一步：先从RingBuffer中获取下一个可以写入的事件的序号
        //第二步：获取对应事件的对象，将数据写入事件对象
        //第三步：将事件提交到RingBuffer。
        //事件只有在提交之后才会通知EventProcessor进行处理。
        //队列的目的就是为生产者和消费者提供一个地方存放要交互的数据，
        //帮助缓冲它们之间传递的消息。这意味着缓冲常常是满的（生产者比消费者快）或者空的（消费者比生产者快）。
        // 生产者和消费者能够步调一致的情况非常少见。
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        for (int i = 0; i < 100; i++) {
            long sequence = ringBuffer.next(); //请求下一个事件序号
            try {
                LongEvent event = ringBuffer.get(sequence); //获取该序号对应的事件对象
                long data = getEventData(); //获取要通过事件传递的业务数据
                event.setValue(data);
            } finally {
                //注意，最后的 ringBuffer.publish 方法必须包含在 finally
                // 中以确保必须得到调用；如果某个请求的 sequence 未被提交，
                // 将会堵塞后续的发布操作或者其它的 producer。
                ringBuffer.publish(sequence); //发布事件
            }
        }
        //关闭Disruptor
        disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
    }

    public long getEventData() {
        Double v = Math.random() * 10000;
        return v.longValue();
    }

    public static void main(String[] args) {
        DisruptorMain main = new DisruptorMain();
        main.startDisruptor();
    }

}
