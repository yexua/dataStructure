package com.at.greedy;

import java.util.*;

/**
 * 贪心算法
 * 应用场景集合覆盖问题
 * 假设存在下面需要付费的广播台，以及广播台信号可以覆盖的地区。如何选择最少的广播台，让所有的地区都可以接收到信号
 * 广播台          覆盖地区
 * K1           “北京"，"上海"，”天津”
 * K2           "广州"，“北京"，“深圳"
 * K3            “成都"，"上海", "杭州”
 * K4           "上海",，“天津"
 * K5             "杭州"，“大连”
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        Set<String> k1 = new HashSet();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        Set<String> k2 = new HashSet();
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");
        Set<String> k3 = new HashSet();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        Set<String> k4 = new HashSet();
        k4.add("上海");
        k4.add("天津");
        Set<String> k5 = new HashSet();
        k5.add("杭州");
        k5.add("大连");
        Map<String, Set<String>> map = new HashMap<>();
        map.put("k1", k1);
        map.put("k2", k2);
        map.put("k3", k3);
        map.put("k4", k4);
        map.put("k5", k5);

        //存放所有地区
        Set<String> allAreas = new HashSet<>();
        for (Map.Entry<String, Set<String>> set : map.entrySet()) {
            for (String s : set.getValue()) {
                allAreas.add(s);
            }
        }

        // 存放电台
        List<String> select = new ArrayList<>();

        //临时集合，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        Set<String> temp = new HashSet<>();

        String maxKey;
        Set<String> area;
        while (allAreas.size() != 0) {
            maxKey = null;
            for (String key : map.keySet()) {
                if (select.contains(key)) {
                    continue;
                }
                temp.clear();
                area = map.get(key);
                temp.addAll(area);
                //将当前电台的地区和所有地区的交集赋给temp
                temp.retainAll(allAreas);
                if (temp.size() > 0 &&
                        (maxKey == null || temp.size() > map.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                select.add(maxKey);
                //清除地区集合中包含该电台覆盖的地区
                allAreas.removeAll(map.get(maxKey));
            }

        }

        System.out.println(select);
    }
}
