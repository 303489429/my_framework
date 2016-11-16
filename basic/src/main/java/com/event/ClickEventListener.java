package com.event;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public class ClickEventListener implements EventListener<ClickEvent> {
    @Override
    public void handlerEvent(ClickEvent event) {
        System.out.println("单击事件处理完成");
    }
}
