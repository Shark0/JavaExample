package com.shark.example.algorithm.leetcode.page2;

import java.util.HashMap;
import java.util.Map;

public class Program91 {
    private final Map<String, Integer> cacheMap = new HashMap<>();

    public int numDecodings(String s) {
        System.out.println("s: " + s);
        Integer cache = cacheMap.get(s);
        if(cache != null) {
            return cache;
        }
        if(s.charAt(0) == '0') {
            return 0;
        }
        int result;


        if(s.length() == 1) {
            result = 1;
        } else {
            result = numDecodings(s.substring(1));
            if(Integer.parseInt(s.substring(0, 2)) <= 26) {
                if(s.length() > 2) {
                    result = result + numDecodings(s.substring(2));
                } else {
                    result ++;
                }

            }
        }
        cacheMap.put(s, result);
        System.out.println("s: " + s + ", result: " + result);
        return result;
    }

    public static void main(String[] argv) {
        System.out.println(new Program91().numDecodings("226"));
    }
}
