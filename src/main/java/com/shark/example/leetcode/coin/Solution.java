package com.shark.example.leetcode.coin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount <= 0) {
            return 0;
        }
        List<Integer> sortCoinList = sortCoins(coins);
        return coinChange(sortCoinList, amount, 0, 0);
    }

    private List<Integer> sortCoins(int[] coins) {
        Integer[] coinList = Arrays.stream( coins ).boxed().toArray( Integer[]::new );
        return Arrays.stream(coinList).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public int coinChange(List<Integer> sortCoinList, int currentAmount, int preChangeCount, int listIndex) {
        if(currentAmount < 0) {
            return -1;
        }
        int result = -1;
        for(int i = listIndex; i < sortCoinList.size(); i ++) {
            Integer coin = sortCoinList.get(listIndex);
            int coinChangeCount = -1;
            if(currentAmount % coin == 0) {
                int currentChangeCount = currentAmount / coin;
                coinChangeCount = preChangeCount + currentChangeCount;
                if(result == -1) {
                    return coinChangeCount;
                }
            } else {
                coinChangeCount = coinChange(sortCoinList, currentAmount - coin, preChangeCount + 1, i);
            }
            if(coinChangeCount != -1) {
                if(result == -1 || coinChangeCount < result) {
                    result = coinChangeCount;
                }
            }
        }
        return result;
    }
}
