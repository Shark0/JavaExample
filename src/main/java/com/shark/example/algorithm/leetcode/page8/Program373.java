package com.shark.example.algorithm.leetcode.page8;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Program373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt(a -> -(a.get(0) + a.get(1))));

        int tempNum2 = Integer.MAX_VALUE;
        int tempNum2Index = Integer.MAX_VALUE;
        for (int num1 : nums1) {
            if(tempNum2Index == 0) {
                break;
            }
            for (int num2Index = 0; num2Index < nums2.length; num2Index++) {
                int num2 = nums2[num2Index];
                if(num2 >= tempNum2) {
                    tempNum2Index = num2Index;
                    break;
                }
                priorityQueue.add(List.of(num1, num2));
                if (priorityQueue.size() > k) {
                    List<Integer> temp = priorityQueue.poll();
                    tempNum2 = temp.get(1);
                }
            }
        }
        List<List<Integer>> results = new ArrayList<>();
        while (!priorityQueue.isEmpty() && results.size() < k) {
            results.add(priorityQueue.poll());
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4, 5, 6};
        int[] nums2 = {3, 5, 7, 9};
        System.out.println(new Gson().toJson(new Program373().kSmallestPairs(nums1, nums2, 20)));
    }
}
