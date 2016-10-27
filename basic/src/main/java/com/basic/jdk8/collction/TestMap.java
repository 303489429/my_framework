package com.basic.jdk8.collction;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by wangzhilong on 2016/10/10.
 */
public class TestMap {

    private Map<Integer,List<Line>> listMap ;
    private List<Line> lineList ;
    private List<Line> getLine(){
       return Arrays.asList(
                new Line(1,"wzl",100),
                new Line(2,"lsi",101),
                new Line(3,"wbs",103),
                new Line(4,"qq",103),
                new Line(5,"zz",101),
                new Line(6,"yy",101)
        );
    }

    public TestMap() {
        this.listMap = new HashMap<>();
        this.lineList = this.getLine();
    }

    void getMergedLine(){
        int a = 1111111111;
        lineList.forEach(this::add);
    }

    private void add(Line line) {
        listMap.computeIfAbsent(line.getUuid(), id -> new ArrayList<>()).add(line);
    }

    private void add2(Line line){
        listMap.computeIfPresent(line.getUuid(), new BiFunction<Integer, List<Line>, List<Line>>() {
            @Override
            public List<Line> apply(Integer integer, List<Line> lines) {
                return null;
            }
        });
    }

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        //map.forEach((id, val) -> System.out.println(id));

        TestMap testMap = new TestMap();
        testMap.getMergedLine();
        testMap.listMap.forEach((id,val) ->System.out.println(id+"--->"+val));
    }
}
