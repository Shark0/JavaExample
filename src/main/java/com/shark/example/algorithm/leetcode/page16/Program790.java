package com.shark.example.algorithm.leetcode.page16;

import java.util.HashMap;

public class Program790 {

    public int numTilings(int n) {
        int[] dominoSets = new int[] {3, 2, 1};
        int[] dominoCounts = new int[]{5, 2, 1};
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < dominoSets.length; i++) {
            int count = n / dominoSets[i];
            map.put(i, count);
            n = n - count * dominoSets[i];
            if(n == 0) {
                break;
            }
        }

        int result = 0;
        for(int index: map.keySet()) {
            int count = map.get(index);
            result = result + count * dominoCounts[index];
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println("result: " + new Program790().numTilings(n));
    }
}
