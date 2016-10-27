package com.concurrent;

/**
 * Created by wangzhilong on 2016/9/6.
 */
public class FinalFieldExample {
    private final int x ;
    private int y ;
    static FinalFieldExample f ;

    public FinalFieldExample() {
        this.x = 3;
        this.y = 4;
    }

    static void writer(){
        f = new FinalFieldExample();
    }

    static void reader(){
        if(f != null){
            int i = f.x ;
            int j = f.y ;
            System.out.println("x="+i+",y="+j);
        }
    }

    public static void main(String[] args) {
        reader();
        writer() ;
    }
}
