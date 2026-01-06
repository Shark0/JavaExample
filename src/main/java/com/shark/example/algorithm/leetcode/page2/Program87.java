package com.shark.example.algorithm.leetcode.page2;

import java.util.HashMap;
import java.util.Map;

public class Program87 {

    private final Map<String, Boolean> map = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        int length = s1.length();
        if(length != s2.length()) {
            return false;
        }
        if(s1.equals(s2)) {
            return true;
        }
        if(length == 1) {
            return false;
        }
        String key = s1 + "," + s2;
        if(map.containsKey(key)) {
            return map.get(key);
        }

        for (int i = 1; i < length; i++) {
            boolean withoutSwap =
                    isScramble(s1.substring(0, i), s2.substring(0, i))
                            && isScramble(s1.substring(i), s2.substring(i));

            if (withoutSwap) {
                map.put(key, true);
                return true;
            }

            if(i == (length - i)) {
                boolean withSwap =
                        isScramble(s1.substring(0, i), s2.substring(length - i))
                                &&
                                isScramble(s1.substring(i), s2.substring(0, length - i));
                if (withSwap) {
                    map.put(key, true);
                    return true;
                }
            }

        }

        map.put(key, false);
        return false;
    }

    public static void main(String[] argv) {
        System.out.println(new Program87().isScramble("great", "rgeat"));
    }
}
