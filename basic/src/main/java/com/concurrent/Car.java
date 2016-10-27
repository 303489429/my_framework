package com.concurrent;

/**
 * Created by wangzhilong on 2016/10/14.
 */
public class Car {
    private String name ;
    private Double id ;

    public Car(String name, Double id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
