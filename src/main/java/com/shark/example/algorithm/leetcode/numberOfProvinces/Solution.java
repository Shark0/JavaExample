package com.shark.example.algorithm.leetcode.numberOfProvinces;

import java.util.*;

public class Solution {

    public static void main(String[] argv) {
        System.out.println(new Solution().findCircleNum(new int[][]{{1,0,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,1,1}}));
    }



    public int findCircleNum(int[][] isConnected) {
        List<Set<Integer>> provinceList = new ArrayList();

        for(int city1 = 0; city1 < isConnected.length; city1 ++) {
            Set<Integer> currentProvince = new HashSet();
            List<Set<Integer>> containedProvinceList = new ArrayList<>();
            for(int city2 = 0; city2 < isConnected[city1].length; city2++) {
                int isConnect = isConnected[city1][city2];
                if(isConnect == 1) {
                    currentProvince.add(city2);
                    for(Set<Integer> set: provinceList) {
                        if(set.contains(city2)) {
                            containedProvinceList.add(set);
                        }
                    }
                }
            }
            provinceList.removeAll(containedProvinceList);
            for(Set province: containedProvinceList) {
                currentProvince.addAll(province);
            }
            provinceList.add(currentProvince);
        }

        return provinceList.size();
    }
}
