package com.shark.example.algorithm.leetcode.program8;

public class StringToInteger {
    public int myAtoi(String s) {
        int charIndex = 0;
        String previewLegalChar = null;
        int result = 0;
        boolean isPositive = true;
        boolean isBreak = false;
        int maxBound = Integer.MAX_VALUE / 10;
        int maxBoundDigit = Integer.MAX_VALUE % 10;
        int minBound = Integer.MIN_VALUE / 10;
        int minBoundDigit = Integer.MIN_VALUE % 10;
        while (charIndex < s.length() && !isBreak) {
            String value = s.substring(charIndex, charIndex + 1);
            switch (value) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    previewLegalChar = value;
                    int number = Integer.parseInt(value);
                    if (isPositive && result >= maxBound) {
                        if(( result > maxBound) || number >= maxBoundDigit) {
                            return Integer.MAX_VALUE;
                        }
                    }

                    if(!isPositive && (result * -1) <= minBound) {
                        if(((result * -1) < minBound) || (number * -1) <= minBoundDigit) {
                            return Integer.MIN_VALUE;
                        }
                    }
                    result = result * 10 + number;
                    break;
                case "+":
                case "-":
                    if (previewLegalChar == null) {
                        previewLegalChar = value;
                        isPositive = "+".equalsIgnoreCase(value);
                    } else {
                        isBreak = true;
                    }
                    break;
                case " ":
                    if (previewLegalChar != null) {
                        isBreak = true;
                    }
                    break;
                default:
                    isBreak = true;
                    break;
            }
            charIndex++;
        }
        return isPositive ? result : (result * -1);
    }

    public static void main(String[] args) {
        StringToInteger stringToInteger = new StringToInteger();
        System.out.println("max = " + Integer.MAX_VALUE);
        System.out.println("max bound digit = " + (Integer.MAX_VALUE % 10));
        System.out.println("min = " + Integer.MIN_VALUE);
        System.out.println("min bound digit = " + (Integer.MIN_VALUE % 10));
        System.out.println("result = " + stringToInteger.myAtoi("21474836460"));
    }
}

