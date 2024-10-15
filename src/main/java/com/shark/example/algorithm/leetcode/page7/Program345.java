package com.shark.example.algorithm.leetcode.page7;

public class Program345 {


    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = s.length() - 1;
        int leftVowelIndex = -1;
        int rightVowelIndex = -1;
        while (l < r) {
            if (leftVowelIndex == -1) {
                char leftChar = chars[l];
                if (leftChar == 'a' || leftChar == 'e' || leftChar == 'i' || leftChar == 'o' || leftChar == 'u' ||
                        leftChar == 'A' || leftChar == 'E' || leftChar == 'I' || leftChar == 'O' || leftChar == 'U'
                ) {
                    leftVowelIndex = l;
                } else {
                    l++;
                }
            } else if (rightVowelIndex == -1) {
                char rightChar = chars[r];
                if (rightChar == 'a' || rightChar == 'e' || rightChar == 'i' || rightChar == 'o' || rightChar == 'u' ||
                        rightChar == 'A' || rightChar == 'E' || rightChar == 'I' || rightChar == 'O' || rightChar == 'U') {
                    rightVowelIndex = r;
                } else {
                    r--;
                }
            } else {
                char temp = chars[leftVowelIndex];
                chars[leftVowelIndex] = chars[rightVowelIndex];
                chars[rightVowelIndex] = temp;
                leftVowelIndex = -1;
                rightVowelIndex = -1;
                l++;
                r--;
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        Program345 program345 = new Program345();
        System.out.println(program345.reverseVowels("aeiou"));
    }
}
