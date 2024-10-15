package com.shark.example.algorithm.leetcode.page19;

import java.util.ArrayList;
import java.util.List;

public class Program933 {

    private final List<Integer> list = new ArrayList<>();
    private int totalCount = 0;
    private int minCount = 0;
    private int minIndex = 0;

    public int ping(int t) {
        totalCount++;
        int min = t - 3000;
        list.add(t);
        for(int i = minIndex; i < list.size(); i++) {
            int value = list.get(i);
            if(value < min) {
                System.out.println("t: " + t + ", min: " + min + ", value: " + value);
                minCount ++;
                minIndex = i + 1;
            } else {
                break;
            }
        }
        System.out.println("total count: " + totalCount + ", min count: " + minCount + ", min: " + min + ", minIndex: " + minIndex);
        return totalCount - minCount;
    }

    public static void main(String[] args) {
        Program933 program933 = new Program933();
        System.out.println("result: " + program933.ping(642));
        System.out.println("result: " + program933.ping(1849));
        System.out.println("result: " + program933.ping(4921));
        System.out.println("result: " + program933.ping(5936));
        System.out.println("result: " + program933.ping(5957));

    }
}
