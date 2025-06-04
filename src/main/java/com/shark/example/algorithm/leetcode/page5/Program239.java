package com.shark.example.algorithm.leetcode.page5;

import java.util.*;

public class Program239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        //init
        int[] result = new int[nums.length - k + 1];
        Map<Integer, Set<Integer>> numberPositionMap = new HashMap<>();
        int max = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Set<Integer> numPositionSet = numberPositionMap.getOrDefault(num, new HashSet<>());
            numPositionSet.add(i);
            numberPositionMap.put(num, numPositionSet);
            if (num > max) {
                max = num;
            }

            if (i >= k) {
                int previewIndex = i - k;
                int preview = nums[previewIndex];
                Set<Integer> previewPositionSet = numberPositionMap.getOrDefault(preview, new HashSet<>());
                previewPositionSet.remove(previewIndex);
                if (previewPositionSet.isEmpty()) {
                    numberPositionMap.remove(preview);
                }
                System.out.println("preview: " + preview + ", max: " + max);
                if (preview == max && previewPositionSet.isEmpty()) {
                    //find next max
                    max--;
                    previewPositionSet = numberPositionMap.get(max);
                    while (previewPositionSet == null) {
                        System.out.println("max: " + max);
                        max--;
                        previewPositionSet = numberPositionMap.get(max);
                    }
                }
            }
            if (i >= (k - 1)) {
                result[i - k + 1] = max;
            }
        }
        return result;
    }


    public int[] maxSlidingWindowV1(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        //init
        int[] result = new int[nums.length - k + 1];
        Set<Integer> maxPositionSet = new HashSet<>();
        int max = findMaxV1(nums, 0, k - 1, maxPositionSet);
        result[0] = max;

        for (int i = k; i < nums.length; i++) {
            int num = nums[i];
            if (num > max) {
                max = num;
                maxPositionSet.clear();
            }
            if (num >= max) {
                maxPositionSet.add(i);
            }

            int preview = nums[i - k];
            if (preview == max) {
                maxPositionSet.remove(i - k);
                if (maxPositionSet.isEmpty()) {
                    max = findMaxV1(nums, i - k + 1, i, maxPositionSet);
                }
            }
            result[i - k + 1] = max;
        }
        return result;
    }

    public int findMaxV1(int[] nums, int start, int end, Set<Integer> maxPositionSet) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            int num = nums[i];
            if (num > max) {
                max = num;
                maxPositionSet.clear();
            }
            if (num >= max) {
                maxPositionSet.add(i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, -1};
        int k = 1;
        int[] results = new Program239().maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(results));
    }

}
