package com.refactor.abstracts;

/**
 * Created by wangzhilong on 2016/8/31.
 */
public abstract class AbstractPerson {
    private int _2BM = 10 * 1024 * 1024;

    public abstract void getName();

    public int getMb(){
        return _2BM ;
    }

}
