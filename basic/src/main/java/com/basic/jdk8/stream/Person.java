package com.basic.jdk8.stream;

import java.util.Date;

/**
 * Created by wangzhilong on 2016/8/30.
 */
public class Person {

    private String name ;
    private int age ;
    private int sex ; //1男 0 女
    private String idCard ;
    private String phone ;
    private Date endTime ;

    public Person(String name, int age, int sex, String idCard, String phone ,Date endTime) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.idCard = idCard;
        this.phone = phone;
        this.endTime = endTime ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", endTime=" + endTime +
                '}';
    }
}
