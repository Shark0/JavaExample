package com.shark.example.leetcode.coin;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Integer, Integer> amountResultMap = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] sortCoin = sort(coins);
        return sortCoinChange(sortCoin, amount);
    }

    private int[] sort(int[] coins) {
        coins = Arrays.stream(coins).sorted().toArray();
        for(int i = 0; i < (coins.length / 2); i ++) {
            int start = coins[i];
            int tail = coins[coins.length - i -1];
            coins[i] = tail;
            coins[coins.length - i - 1] = start;
        }
        return coins;
    }

    public int sortCoinChange(int[] coins, int amount) {
        int coinChange = -1;
        for (int i = 0; i < coins.length; i++) {
            Integer coin = coins[i];
            int tempCoinChange = -1;
            if (amount % coin == 0) {
                tempCoinChange = amount / coin;
                if (coinChange == -1 || (tempCoinChange < coinChange)) {
                    amountResultMap.put(amount, tempCoinChange);
                    return tempCoinChange;
                }
            } else {
                int nextAmount = amount - coin;
                if (nextAmount < 0) {
                    continue;
                }
                int nextCoinChange;
                if (amountResultMap.get(nextAmount) != null) {
                    nextCoinChange = amountResultMap.get(nextAmount);
                } else {
                    nextCoinChange = sortCoinChange(coins, nextAmount);
                }
                if (nextCoinChange > 0) {
                    tempCoinChange = nextCoinChange + 1;
                }
            }
            if ((tempCoinChange > 0) && (coinChange == -1 || (tempCoinChange < coinChange))) {
                coinChange = tempCoinChange;
            }
        }
        amountResultMap.put(amount, coinChange);
        return coinChange;
    }
}
