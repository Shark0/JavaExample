package com.shark.example.algorithm.leetcode.page15;

public class Program714 {
    public int maxProfit(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;

        for (int price : prices) {
            buy = Math.max(buy, sell - price);
            sell = Math.max(sell, buy + price - fee);
            System.out.println("price: " + price + ", buy: " + buy + ", sell: " + sell);
        }

        return sell;
    }

    public static void main(String[] args) {
        int[] prices = {9, 4, 8, 2, 3, 1};
        int fee = 2;
        System.out.println("result: " + new Program714().maxProfit(prices, fee));
    }
}
