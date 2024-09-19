package com.shark.example.algorithm.leetcode.program38;

public class Program38 {

    public String countAndSay(int n) {
        int i = 2;
        String lastText = "1";
        while (i <= n) {
            int lastCharCount = 0;
            char lastChar = '0';
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < lastText.length(); j++) {
                char currentChar = lastText.charAt(j);
                System.out.println("lastChar: " + lastChar + ", currentChar: " + currentChar);
                if(currentChar == lastChar) {
                    lastCharCount ++;
                } else {
                    if(lastCharCount != 0) {
                        stringBuilder.append(lastCharCount).append(lastChar);
                    }
                    lastChar = currentChar;
                    lastCharCount = 1;
                }
            }
            if(lastCharCount != 0) {
                stringBuilder.append(lastCharCount).append(lastChar);
            }
            lastText = stringBuilder.toString();
            i ++;
        }
        return lastText;
    }

    public static void main(String[] args) {
        Program38 program38 = new Program38();
        String result = program38.countAndSay(3);
        System.out.println("result: " + result);
    }
}
