package com.concurrent;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by wangzhilong on 2016/10/14.
 */
public class TestWeakRefrence {
    public static void main(String[] args) {
        ReferenceQueue<Car> referenceQueue = new ReferenceQueue<>();
        Car car = new Car("汽车",10.8);

        WeakReference<Car> nameCahce = new WeakReference<Car>(car,referenceQueue);  //弱引用在GC时会被回收
        int i=0 ;
        while (true){
            if(nameCahce.get() != null){
                i++ ;
                System.out.println("cache is alive for "+i+" loops -"+nameCahce.get());
            }else {
                System.err.println("chache has been collected.");
                break;
            }
        }

        while (true){
            System.out.println(referenceQueue.toString());
            Reference<? extends Car> reference = referenceQueue.poll();
            if(reference != null){
                System.out.println(reference.get());
            }
            //if(reference.get())
            //System.out.println(reference.get());
        }

    }
}
