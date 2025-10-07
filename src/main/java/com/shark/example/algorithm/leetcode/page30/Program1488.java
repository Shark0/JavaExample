package com.shark.example.algorithm.leetcode.page30;

import java.util.*;

public class Program1488 {
    public int[] avoidFlood(int[] rains) {
        int[] result = new int[rains.length];
        Set<Integer> lakeSet = new HashSet<>();
        Map<Integer, Integer> lakeRainDayMap = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        for (int day = 0; day < rains.length; day++) {
            int lake = rains[day];
            if (lake > 0) {
                if (lakeSet.contains(lake)) {
                    Integer rainDay = lakeRainDayMap.get(lake);
                    int dryDay = findDryDay(dryDays, rainDay);
                    if(dryDay == - 1) {
                        return new int[0];
                    } else {
                        result[dryDay] = lake;
                    }
                }
                lakeSet.add(lake);
                lakeRainDayMap.put(lake, day);
                result[day] = -1;
            } else {
                result[day] = 1;
                dryDays.add(day);
            }
        }
        return result;
    }

    private int findDryDay(TreeSet<Integer> dryDays, int rainDay) {
        Integer dryDay = dryDays.ceiling(rainDay + 1);
        if (dryDay != null) {
            dryDays.remove(dryDay);
            return dryDay;
        }
        return -1;
    }

    public static void main(String[] argv) {
        int[] result = new Program1488().avoidFlood(new int[]{1, 0, 2, 0, 2, 1});
        System.out.printf(Arrays.toString(result));
    }
}
