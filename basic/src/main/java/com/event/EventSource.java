package com.event;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public interface EventSource {
    void addEventListener(EventListener eventListener);
    void removeEventListener(EventListener eventListener);
    void notifyListener(Event event);
}
