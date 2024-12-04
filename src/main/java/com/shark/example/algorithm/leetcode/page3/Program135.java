package com.shark.example.algorithm.leetcode.page3;

import java.util.Arrays;

public class Program135 {

    public int candy(int[] ratings) {
        int ratingSize = ratings.length;
        int[] candies = new int[ratingSize];
        Arrays.fill(candies, 1);
        //compare next
        for (int i = 1; i < ratingSize; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        System.out.println("candies1: " + Arrays.toString(candies));
        //compare preview
        for (int i = ratingSize - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1]) {
                candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
            }
        }
        System.out.println("candies2: " + Arrays.toString(candies));
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }
        return totalCandies;
    }

    public static void main(String[] args) {
        System.out.println(new Program135().candy(new int[]{1, 3, 2, 2, 1}));
    }
}
