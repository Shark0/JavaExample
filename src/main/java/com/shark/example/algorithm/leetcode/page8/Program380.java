package com.shark.example.algorithm.leetcode.page8;

import java.util.HashSet;
import java.util.Set;

public class Program380 {

    public static void main(String[] args) {

    }

    static class RandomizedSet {
        private final Set<Integer> set;

        public RandomizedSet() {
            set = new HashSet<>();
        }

        public boolean insert(int val) {
            return set.add(val);
        }

        public boolean remove(int val) {
            return set.remove(val);
        }

        public int getRandom() {
            int random = (int) (Math.random() * set.size());
            int i = 0;
            for (Integer value : set) {
                if (i == random) {
                    return value;
                }
                i++;
            }
            return 0;
        }
    }

}
