package com.basic.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

/**
 * Created by wangzhilong on 2016/9/5.
 */
public class TestJson {
    private static final Gson GSON = (new GsonBuilder())
            .setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

    public static void main(String[] args) {
        Set<Integer> set = Sets.newHashSet();
        set.add(1);
        set.add(4);
        System.out.println(JSON.toJSONString(set));
        //
        Set<JsonObject> sets = Sets.newHashSet();
        JsonObject object = new JsonObject();
        sets.add(object);
        object.setDate(new Date());
        object.setName("wzl");
        JsonObject object2 = new JsonObject();
        sets.add(object2);
        object2.setDate(new Date());
        object2.setName("张三");

        System.out.println(GSON.toJson(object));

        String str = GSON.toJson(sets) ;
        System.out.println(str);

        Set<JsonObject> sets2 =  GSON.fromJson("[]",Set.class);
        System.out.println("sets2"+sets2.toString());

        String jsonstr = "[{\"name\":\"张三\",\"date\":null}]";

        List<JsonObject> list = GSON.fromJson(jsonstr,new TypeToken<ArrayList<JsonObject>>(){}.getType()) ;
        System.out.println("jsonstr:"+list.get(0).getDate());

        Set<Integer> setInt = new HashSet<>();
        setInt.add(1);
        setInt.add(9);

        String jsonSets = GSON.toJson(setInt);
        System.out.println("jsonSets"+jsonSets);

        Set<Integer> lineIds = GSON.fromJson(jsonSets,new TypeToken<Set<Integer>>(){}.getType()) ;
        System.out.println("lineIds:"+lineIds);
        lineIds.stream().forEach(System.out::println);
    }
}
