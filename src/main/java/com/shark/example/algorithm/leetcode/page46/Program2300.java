package com.shark.example.algorithm.leetcode.page46;

import com.google.gson.Gson;

import java.util.Arrays;

public class Program2300 {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] results = new int[spells.length];
        Arrays.sort(potions);
        int minSuccessSpell = (int) (success / potions[0] + ((success % potions[0] == 0)? 0: 1));
        for (int i = 0; i < results.length; i++) {
            int spell = spells[i];
            if(spell >= minSuccessSpell) {
                results[i] = potions.length;
            } else {
                results[i] = binarySearch(spells[i], potions, success);
            }
        }
        return results;
    }

    private int binarySearch(int spell, int[] potions, long success) {
        int head = 0;
        int tail = potions.length - 1;

        while (head < tail) {
            int mid = (head + tail) / 2;
            if ((long) spell * potions[mid] < success) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        if (head == potions.length - 1) {
            if ((long) spell * potions[head] < success) {
                return 0;
            }
        }
        return potions.length - head;
    }

    public static void main(String[] args) {
        Program2300 program2300 = new Program2300();
        int[] spells = new int[]{5, 1, 3};
        int[] potions = new int[]{1, 2, 3, 4, 5};
        int success = 7;
        System.out.println("results: " + new Gson().toJson(program2300.successfulPairs(spells, potions, success)));
    }
}
