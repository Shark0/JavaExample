package com.shark.example.algorithm.leetcode.page3;

public class Program123 {

    public int maxProfit(int[] prices) {
        if(prices.length < 2) {
            return 0;
        }
        int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;

        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
            System.out.println("price: " + price + " buy1: " + buy1 + " sell1: " + sell1 + " buy2: " + buy2 + " sell2: " + sell2);
        }
        return sell2;
    }

    public static void main(String[] args) {
        System.out.println(new Program123().maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }
}
