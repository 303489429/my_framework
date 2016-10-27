package com.basic.jdk8.collction;

/**
 * Created by wangzhilong on 2016/10/11.
 */
public class Fee {
    private int id ;
    private String name ;

    public Fee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
