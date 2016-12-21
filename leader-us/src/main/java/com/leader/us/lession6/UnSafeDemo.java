package com.leader.us.lession6;

import com.leader.us.tool.UnsafeUtil;
import sun.misc.Unsafe;

import java.util.ArrayList;

/**
 * Created by wangzhilong on 2016/11/15.
 */
public class UnSafeDemo {
    private static Unsafe unsafe = UnsafeUtil.getUnsafe();

    public static void main(String[] args) {
        System.out.println("array ArrayList scale:"+ unsafe.arrayIndexScale(ArrayList[].class));  //4
        System.out.println("array MyObj scale:"+ unsafe.arrayIndexScale(MyObj[].class));  // 4
        System.out.println("array long scale:"+ unsafe.arrayIndexScale(long[].class));  //8
        System.out.println("array boolean scale:"+ unsafe.arrayIndexScale(boolean[].class)); //1
        System.out.println("array Boolean scale:"+ unsafe.arrayIndexScale(Boolean[].class)); //4
        System.out.println("array int scale:"+ unsafe.arrayIndexScale(int[].class)); //4
        System.out.println("array Integer scale:"+ unsafe.arrayIndexScale(Integer[].class)); //4
        System.out.println("array byte scale:"+ unsafe.arrayIndexScale(byte[].class)); //1
        System.out.println("array short scale:"+ unsafe.arrayIndexScale(short[].class)); //2

        Integer[] longArr = {1,4,6,7,8,9};
        long baseOffset = unsafe.arrayBaseOffset(longArr.getClass()) ; //数组第一个元素的偏移地址
        long scaleOffset = unsafe.arrayIndexScale(longArr.getClass());  //可以获取数组的转换因子，也就是数组中元素的增量地址
        System.out.println("array long base size :"+baseOffset+",scale offset:"+scaleOffset);
        Integer one = (Integer) unsafe.getObject(longArr,baseOffset+4+4);
        System.out.println("one :"+one);
        System.out.println(unsafe.getInt(longArr,baseOffset+12));

        long[] logArr = {1,4,6,7,8,9};
        System.out.println("log base offset:"+unsafe.arrayBaseOffset(logArr.getClass())+",long sacle offset:"+unsafe.arrayIndexScale(logArr.getClass()));
        System.out.println("#############################");
        while (true){
            Object obj = null;  //当前偏移位置没有值时直接异常
            try {
                obj = unsafe.getObject(longArr,baseOffset);
            } catch (Exception e) {
            }
            if(obj != null && obj instanceof Integer){
                System.out.println("offset="+baseOffset+" is obj value="+(Integer) obj);
            }else{
                break;
            }
            baseOffset += scaleOffset ;
        }
    }
}
