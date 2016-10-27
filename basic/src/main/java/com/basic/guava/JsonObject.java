package com.basic.guava;

import java.util.Date;

/**
 * Created by wangzhilong on 2016/9/5.
 */
public class JsonObject {
   private String name ;
   private Date date ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
