package com.shark.example.algorithm.leetcode.page2;

public class Program58 {

    public int lengthOfLastWord(String s) {
        int previewLength = 0;
        int currentLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (currentLength != 0) {
                    previewLength = currentLength;
                }
                currentLength = 0;
            } else {
                currentLength++;
            }
        }
        if (currentLength > 0) {
            return currentLength;
        }
        return previewLength;
    }

    public static void main(String[] args) {
        System.out.println(new Program58().lengthOfLastWord("luffy is still joyboy"));
    }
}
