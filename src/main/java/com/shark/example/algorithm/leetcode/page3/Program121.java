package com.shark.example.algorithm.leetcode.page3;

public class Program121 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int max = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if(price < minPrice) {
                minPrice = price;
            }
            max = Math.max(max, price - minPrice);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Program121().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
