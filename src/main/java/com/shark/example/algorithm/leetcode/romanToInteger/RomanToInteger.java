package com.shark.example.algorithm.leetcode.romanToInteger;

public class RomanToInteger {

    public int romanToInt(String s) {
        int result = 0;
        int i = 0;
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            char nextChar = 0;
            switch (currentChar) {
                case 'M':
                    result += 1000;
                    i ++;
                    break;
                case 'D':
                    result += 500;
                    i ++;
                    break;
                case 'C':
                    if (i + 1 < s.length()) {
                        nextChar = s.charAt(i + 1);
                    }
                    if('D' == nextChar) {
                        result += 400;
                        i = i + 2;
                    } else if ('M' == nextChar) {
                        result += 900;
                        i = i + 2;
                    } else {
                        result += 100;
                        i ++;
                    }
                    break;
                case 'L':
                    result += 50;
                    i ++;
                    break;
                case 'X':
                    if (i + 1 < s.length()) {
                        nextChar = s.charAt(i + 1);
                    }
                    if('L' == nextChar) {
                        result += 40;
                        i = i + 2;
                    } else if ('C' == nextChar) {
                        result += 90;
                        i = i + 2;
                    } else {
                        result += 10;
                        i ++;
                    }
                    break;
                case 'V':
                    result += 5;
                    i ++;
                    break;
                case 'I':
                    if (i + 1 < s.length()) {
                        nextChar = s.charAt(i + 1);
                    }
                    if('V' == nextChar) {
                        result += 4;
                        i = i + 2;
                    } else if ('X' == nextChar) {
                        result += 9;
                        i = i + 2;
                    } else {
                        result += 1;
                        i ++;
                    }
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();
        System.out.println(romanToInteger.romanToInt("MCMXCIV"));
    }
}
