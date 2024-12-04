package com.shark.example.algorithm.leetcode.page3;

public class Program125 {

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char leftChar = s.charAt(left);
            boolean isLeftCharValid = isValidChar(leftChar);
            if(!isLeftCharValid) {
                left++;
                continue;
            }
            char rightChar = s.charAt(right);
            boolean isRightCharValid = isValidChar(rightChar);
            if(!isRightCharValid) {
                right--;
                continue;
            }
            if(leftChar != rightChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isValidChar(char c) {
        return switch (c) {
            case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> true;
            default -> false;
        };
    }

    public static void main(String[] args) {
        String test1 = "A man, a plan, a canal: Panama";
        String test2 = "AB@A";
        System.out.println(new Program125().isPalindrome(test1));
    }
}
