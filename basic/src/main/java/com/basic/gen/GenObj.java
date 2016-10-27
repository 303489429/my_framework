package com.basic.gen;

/**
 * Created by wangzhilong on 2016/9/14.
 */
public class GenObj<T extends Annimal> {
    private T obj ;

    public GenObj(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        GenObj<Dog> genObj = new GenObj<Dog>(dog) ;
        genObj.getObj().eat();

        Fish fish = new Fish();
        GenObj<Fish> fishGenObj = new GenObj<>(fish);
        fishGenObj.getObj().eat();

        GenObj<?> gclass = null ;
        gclass = genObj ;
        ((Dog)gclass.getObj()).eat();

        GenObj<? extends Annimal> sub = null ;
        sub = genObj ;
        sub = fishGenObj ;

        //GenObj<? super Dog> sub2 = fishGenObj ;

    }
}

abstract class Annimal {
    abstract void eat();
}

class Dog extends Annimal{
    @Override
    void eat() {
        System.out.println("狗啃骨头");
    }
}

class Fish extends Annimal{
    @Override
    void eat() {
        System.out.println("猫吃鱼");
    }
}


