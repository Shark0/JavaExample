package com.shark.example.algorithm.leetcode.page3;

public class Program122 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int profit = 0;
        int buy = -1;

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            boolean hasNext = i < prices.length - 1;
            //sell
            if (buy != -1) {
                if (price > buy) {
                    profit = profit + price;
                    System.out.println("profit = " + profit + " buy = " + buy + " price = " + price);
                    buy = -1;

                }
            }
            //buy
            if (buy == -1) {
                if (hasNext) {
                    int nextPrice = prices[i + 1];
                    if (nextPrice > price) {
                        profit = profit - price;
                        buy = price;
                        System.out.println("buy = " + buy);
                    }
                }
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(new Program122().maxProfit(new int[]{2, 1, 2, 0, 1}));
    }

}
