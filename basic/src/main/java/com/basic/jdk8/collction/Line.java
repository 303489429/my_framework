package com.basic.jdk8.collction;

/**
 * Created by wangzhilong on 2016/10/10.
 */
public class Line {
    private int id ;
    private String name ;
    private int uuid ;

    public Line(int id, String name, int uuid) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
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

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
