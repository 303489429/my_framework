package com.event;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public class Client {

    public static void main(String[] args) {
        Button button = new Button();
        button.addEventListener(new ClickEventListener());
        button.addEventListener(new DbClickEventListener());
        button.notifyListener(new DbClickEvent());
        button.notifyListener(new ClickEvent());
    }
}
