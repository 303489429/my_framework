package com.spring.bean;

/**
 * Created by wangzhilong on 2016/10/8.
 */
public class Main extends AutowireComponent {

    public void autowireBean(){
        Print print = autowireBean(new Print());
        print.printMsg();
    }

    public void mualBean(){
        Print print = new Print();
        print.printObj();
    }

}
