package com.basic.validate;

import com.alibaba.fastjson.JSON;
import com.basic.jdk8.stream.Person;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by wangzhilong on 2016/9/1.
 */
public class TestPOJO {
    public static void main(String[] args) {
        POJO pojo = new POJO();
        pojo.setName(100012);
        pojo.setUuid("0973942");
        pojo.setP1("2222222208");
        pojo.setP2("2222222208");
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            BeanUtils.copyProperties(pojo ,new Bean());
        }
        long end1 = System.currentTimeMillis();
        System.out.println("bean copy time:"+(end1-start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Bean bean = new Bean();
            bean.setName(pojo.getName());
            bean.setUuid(pojo.getUuid());
            bean.setP1(pojo.getP1());
            bean.setP2(pojo.getP2());
        }
        long end2 = System.currentTimeMillis();
        System.out.println("new init time:"+(end2-start2));



        Consumer<Integer> a = System::exit;
        Consumer<String[]> consumer = Arrays::sort;  //不返回值
        BiConsumer<Person[], Comparator<? super Person>> biConsumer = Arrays::sort; //不返回值
    }
}
