package com.shark.example.algorithm.leetcode.page1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Program3 {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int index = 0;
        int startIndex = 0;
        int length = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            Integer preCharIndex = map.get(c);
            System.out.println("index: " + index + ", preCharIndex: " + preCharIndex + ", startIndex: " + startIndex);
            if (preCharIndex != null && preCharIndex >= startIndex) {
                startIndex = preCharIndex + 1;
            } else {
                int tempLength  = index - startIndex + 1;
                if(tempLength > length) {
                    length = tempLength;
                }
                System.out.println("startIndex: " + startIndex + ", index: " + index +
                        ", tempLength = " + tempLength + ", c: " + c);
            }
            map.put(c, index);
            index ++;
            System.out.println();
        }
        return length;
    }

    public int lengthOfLongestSubstring1(String s) {
        int index = 0;
        int length = 0;
        while (index < s.length() && index + length <= s.length()) {
            int tempLength = 0;
            Set<Character> set = new HashSet<>();
            for(int i = index; i < s.length(); i++) {
                char c = s.charAt(i);
                if(set.contains(c)) {
                    break;
                }
                tempLength  = i - index + 1;
                System.out.println("c: " + c + ", set.contains(c): " + set.contains(c));
                set.add(c);
            }
            if(tempLength > length) {
                length = tempLength;
            }
            index ++;
        }

        return length;
    }

    public static void main(String[] args) {
        String input = "abcabcbb";
        Program3 program3 = new Program3();
        int result = program3.lengthOfLongestSubstring(input);
        System.out.println(result);
    }
}
