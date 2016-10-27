package com.basic.gen;

/**
 * Created by wangzhilong on 2016/9/14.
 */
public class GenClass<T,R> extends SupperClass<T> implements IInfo<R>{
    private T var1;
    private R var2 ;

    public GenClass(T var1 , R var2) {
        super(var1);
        this.var1 = var1;
        this.var2 = var2 ;
    }

    public T show1() {
        return super.show1();
    }

    @Override
    public void show2(R var3) {
        System.out.println(var3);
        System.out.println(var2);
    }

    public static void main(String[] args) {
        GenClass<String,Integer> class1 = new GenClass<>("wzl",20);
        class1.show2(100);
    }
}
interface  IInfo<R> {
    void show2(R var3);
}
class SupperClass<T> {
    private T var1 ;
    public SupperClass(T var1){
        this.var1 = var1 ;
    }

    public T show1(){
        return var1 ;
    }
}
