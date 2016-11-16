package com.event;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public class Button implements EventSource {
    private List<EventListener> listeners = new LinkedList<>();

    @Override
    public void addEventListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void removeEventListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

    @Override
    public void notifyListener(Event event) {
        listeners.stream().forEach(eventListener -> {
            try {
                eventListener.handlerEvent(event);
            }catch (Exception e){
            }
        });
    }
}
