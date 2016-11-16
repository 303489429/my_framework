package com.event;

/**
 * Listener 通用
 * Created by wangzhilong on 2016/11/16.
 */
public interface EventListener<T extends Event> {
    void handlerEvent(T event);
}
