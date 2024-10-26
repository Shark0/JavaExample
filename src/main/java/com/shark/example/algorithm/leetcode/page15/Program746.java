package com.shark.example.algorithm.leetcode.page15;

import java.util.Arrays;

public class Program746 {

    public int minCostClimbingStairs(int[] cost) {
        int[] resultMinCostDp = new int[cost.length];
        Arrays.fill(resultMinCostDp, -1);
        int pay1 = minCost(0, cost, resultMinCostDp);
        int pay2 = minCost(1, cost, resultMinCostDp);
        return Math.min(pay1, pay2);
    }

    public int minCost(int index, int[] cost, int[] resultMinCostDp) {
        int pay = resultMinCostDp[index];
        if(pay != -1) {
            return pay;
        }
        if (index < cost.length) {
            pay = cost[index];
        }
        if(index + 2 < cost.length) {
            int nextPay1 = minCost(index + 1, cost, resultMinCostDp);
            int nextPay2 = minCost(index + 2, cost, resultMinCostDp);;
            pay = pay + Math.min(nextPay1, nextPay2);
        }
        resultMinCostDp[index] = pay;
        return pay;
    }

    public static void main(String[] args) {
        int[] cost = new int[]{10, 15, 20};
        System.out.println("result: " + new Program746().minCostClimbingStairs(cost));
    }
}
