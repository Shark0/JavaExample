package com.shark.example.algorithm.leetcode.page8;

public class Program394 {
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        int index = 0;
        int openIndex;
        int closeIndex = 0;
        int openCount;
        while (index < s.length()) {
            char c = s.charAt(index);
            System.out.println("index: " + index + ", c: " + c);
            boolean isInteger = c >= '0' && c <= '9';;
            if (isInteger) {
                count = count * 10 + (c - '0');
            }
            if(c == '[') {
                // find close
                openIndex = index + 1;
                openCount = 1;
                System.out.println("openCount: " + openCount);
                index ++;
                while(openCount != 0) {
                    char subC = s.charAt(index);
                    System.out.println("index: " + index + ", subC: " + subC);
                    if(subC == '[') {
                        openCount ++;
                        System.out.println("openCount: " + openCount);
                    }
                    if(subC == ']') {
                        openCount --;
                        System.out.println("openCount: " + openCount);
                    }
                    if(openCount == 0) {
                        closeIndex = index;
                    }
                    index ++;
                }

                String subS = s.substring(openIndex, closeIndex);
                System.out.println("openIndex: " + openIndex + ", closeIndex: " + closeIndex +
                        ", subS: " + subS + ", count: " + count);

                for(int i = 0; i < count; i++) {
                    result.append(decodeString(subS));
                }

                count = 0;
            } else {
                if(!isInteger) {
                    result.append(c);
                }
                index ++;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Program394 program394 = new Program394();
        String result = program394.decodeString("3[a2[c]]");
        System.out.println("result: " + result);
    }

}
