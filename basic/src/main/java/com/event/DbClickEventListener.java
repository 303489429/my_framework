package com.event;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public class DbClickEventListener implements EventListener<DbClickEvent> {
    @Override
    public void handlerEvent(DbClickEvent event) {
        System.out.println("双击事件完成");
    }
}
