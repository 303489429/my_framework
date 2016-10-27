package com.wzl.sort;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhilong on 2016/10/26.
 */
public class SortMain {

    public static void main(String[] args) {
        Collection<PriceOrdering> priceOrderings = ImmutableList.of(
                new PriceOrdering(45.23),
                new PriceOrdering(45.22),
                new PriceOrdering(89.39),
                new PriceOrdering(12.39),
                new PriceOrdering(98)
        );

        new SortingComputer<>(priceOrderings).computer();

        priceOrderings.stream().forEach(System.out::println);

        Map<Integer/* project_id */, Integer> mapWithGroupIdIsNotNull = Maps.newHashMap();
        mapWithGroupIdIsNotNull.put(1,2);
        Map<Integer/* project_id */, Integer> map = Maps.newHashMap();
        for (Integer projectId : mapWithGroupIdIsNotNull.keySet()) {
            int temp = map.get(projectId) == null ? 0 : map.get(projectId) ;
            map.put(projectId, temp + (mapWithGroupIdIsNotNull.get(projectId) == null ? 0 : mapWithGroupIdIsNotNull.get(projectId)));
        }
    }
}
