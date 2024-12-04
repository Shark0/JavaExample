package com.shark.example.algorithm.leetcode.page6;

public class Program274 {

    public int hIndex(int[] citations) {
        int[] hIndexArray = new int[citations.length];
        int hIndex = 0;

        for (int citation : citations) {
            for (int i = 0; i < hIndexArray.length; i++) {
                if (citation - 1 >= i) {
                    hIndexArray[i] = hIndexArray[i] + 1;
                    if (hIndexArray[i] >= i + 1) {
                        hIndex = i + 1;
                    }
                } else {
                    break;
                }
            }
        }
        return hIndex;
    }

    public static void main(String[] args) {
        System.out.println(new Program274().hIndex(new int[]{1, 3, 1}));
    }
}
