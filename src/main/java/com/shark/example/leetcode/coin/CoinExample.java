package com.shark.example.leetcode.coin;

public class CoinExample {
    public static void main(String[] argv) {
        int[] coins = {10 , 7};
        int result = new Solution().coinChange(coins, 84);
        System.out.println(result);
    }
}
