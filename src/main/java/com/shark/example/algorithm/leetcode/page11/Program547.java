package com.shark.example.algorithm.leetcode.page11;

import java.util.*;

public class Program547 {

    public static void main(String[] argv) {
        System.out.println(new Program547().findCircleNum(new int[][]{{1,0,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,1,1}}));
    }

    public int findCircleNum(int[][] isConnected) {
        List<Set<Integer>> provinceList = new ArrayList<>();

        for (int[] cityPaths : isConnected) {
            Set<Integer> currentProvince = new HashSet<>();
            List<Set<Integer>> containedProvinceList = new ArrayList<>();
            for (int city2 = 0; city2 < cityPaths.length; city2++) {
                int isConnect = cityPaths[city2];
                if (isConnect == 1) {
                    currentProvince.add(city2);
                    for (Set<Integer> set : provinceList) {
                        if (set.contains(city2)) {
                            containedProvinceList.add(set);
                        }
                    }
                }
            }
            provinceList.removeAll(containedProvinceList);
            for (Set<Integer> province : containedProvinceList) {
                currentProvince.addAll(province);
            }
            provinceList.add(currentProvince);
        }

        return provinceList.size();
    }
}
