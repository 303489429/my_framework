package com.basic.jdk8.collction;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by wangzhilong on 2016/10/11.
 */
public class TestPrice {
    public static void main(String[] args) {
        Collection<Price> price1 = Arrays.asList(
                new Price("aaa",new BigDecimal(10)),
                new Price("www",new BigDecimal(21)),
                new Price("qqq",new BigDecimal(45))
        ) ;
        price1.stream().filter(p -> p.getValue().intValue() > 20).forEach(System.out::println);
        Collection<Price> price2 = Arrays.asList(
                new Price("aaa",new BigDecimal(9)),
                new Price("www",new BigDecimal(8)),
                new Price("qqq",new BigDecimal(5))
        );
        Collection<Price> price3 = Arrays.asList(
                new Price("aaa",new BigDecimal(1)),
                //new Price("www",new BigDecimal(1)),
                new Price("qqq",new BigDecimal(1))
        );

        Map<Integer,Collection<Price>> map = Maps.newHashMap();
        map.put(1,price1);
        map.put(2,price2) ;
        //map.put(3,price3);
        Map<String,MergedPrice> mergedPriceMap = Maps.newHashMap();
        for (Integer line : map.keySet()){
            Collection<Price> prices = map.get(line) ;
            if(mergedPriceMap.size() > 0){
                if(prices.size() != mergedPriceMap.size()){
                    throw new RuntimeException("投标方没有完全报价");
                }
            }
            for(Price price : prices){
                mergedPriceMap.computeIfAbsent(price.getOrgCode(), s -> new MergedPrice(price.getOrgCode())).addValue(price.getValue());
            }
            //mergedPriceMap.forEach((s, mergedPrice) -> System.out.println(s+":"+line+":"+ JSON.toJSONString(mergedPrice)));
        }

        mergedPriceMap.forEach((s, mergedPrice) -> System.out.println(s+"2:"+ JSON.toJSONString(mergedPrice)));

    }

    private static void assentEquals(Map<Integer,Collection<Price>> map){
        //Map<String,>
        for (Integer lineId : map.keySet()){

        }
    }
}
