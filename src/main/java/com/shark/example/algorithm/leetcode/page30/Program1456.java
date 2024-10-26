package com.shark.example.algorithm.leetcode.page30;

public class Program1456 {

    public int maxVowels(String s, int k) {
        int count = 0;
        int currentCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                currentCount++;
            }
            if (i >= k) {
                char previewC = s.charAt(i - k);
                if (previewC == 'a' || previewC == 'e' || previewC == 'i' || previewC == 'o' || previewC == 'u') {
                    currentCount--;
                }
            }
            if(currentCount == k){
                return k;
            }
            if(currentCount > count){
                count = currentCount;
            }
            System.out.println("i: " + i + ", c: " + c +", count: " + count);
        }
        return count;
    }

    public static void main(String[] args) {
        Program1456 program1456 = new Program1456();
        System.out.println("result: " + program1456.maxVowels("abciiidef", 3));
    }
}
