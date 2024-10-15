package com.shark.example.algorithm.leetcode.page22;

public class Program1071 {

    public String gcdOfStrings(String str1, String str2) {
        // Check if concatenated strings are equal or not, if not return ""
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // If strings are equal than return the substring from 0 to gcd of size(str1), size(str2)
        int gcd = gcd(str1.length(), str2.length());
        return str1.substring(0, gcd);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public String gcdOfStrings1(String str1, String str2) {
        if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {
            return "";
        }

        char[] str2Chars = str2.toCharArray();
        for (int i = 0; i < str2.length(); i++) {
            int resultLength = str2Chars.length - i;
            if (str1.length() % resultLength == 0 && str2.length() % resultLength == 0) {
                int str1Index = 0;
                boolean isMatchStr1 = true;
                while (str1Index < str1.length()) {
                    boolean tempMatch = true;
                    for (int resultIndex = 0; resultIndex < resultLength; resultIndex++) {
                        tempMatch = tempMatch && str1.charAt(str1Index + resultIndex) == str2Chars[resultIndex];
                    }
                    isMatchStr1 = tempMatch;
                    if (!isMatchStr1) {
                        break;
                    }
                    str1Index = str1Index + resultLength;
                }
                if (!isMatchStr1) {
                    continue;
                }
                int str2Index = 0;
                boolean isMatchStr2 = true;
                while (str2Index < str2.length()) {
                    boolean tempMatch = true;
                    for (int resultIndex = 0; resultIndex < resultLength; resultIndex++) {
                        tempMatch = tempMatch && str2.charAt(str2Index + resultIndex) == str2Chars[resultIndex];
                    }
                    isMatchStr2 = tempMatch;
                    if (!isMatchStr2) {
                        break;
                    }
                    str2Index = str2Index + resultLength;
                }
                if (!isMatchStr2) {
                    continue;
                }
                char[] resultChars = new char[resultLength];
                System.arraycopy(str2Chars, 0, resultChars, 0, resultLength);
                return new String(resultChars);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Program1071 program1071 = new Program1071();
        System.out.println(program1071.gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX"));
    }

}
