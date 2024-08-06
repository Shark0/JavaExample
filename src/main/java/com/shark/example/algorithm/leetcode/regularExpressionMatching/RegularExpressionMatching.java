package com.shark.example.algorithm.leetcode.regularExpressionMatching;

public class RegularExpressionMatching {
    public boolean isMatch(String sentence, String pattern) {
        int currentSentenceIndex = 0;

        String lastMatchPattern = "";
        String lastMatchChar = "";

        int currentMatchPatternIndex = 0;
        String currentMatchPattern = "";
        String currentMatchChar;

        while (currentSentenceIndex < sentence.length()) {
            boolean isMatch;
            if(currentMatchPatternIndex == pattern.length()) {
                return false;
            }

            String currentSentenceChar = sentence.substring(currentSentenceIndex, currentSentenceIndex + 1);
            currentMatchPattern = pattern.substring(currentMatchPatternIndex, currentMatchPatternIndex + 1);
            currentMatchChar = currentMatchPattern;
            if((currentMatchPatternIndex + 1) < pattern.length() &&
                    "*".equalsIgnoreCase(pattern.substring(currentMatchPatternIndex + 1, currentMatchPatternIndex + 2))) {
                currentMatchPattern = currentMatchPattern + "*";
                // ex: a*
            }

            isMatch = currentMatchChar.equals(".") || currentSentenceChar.equalsIgnoreCase(currentMatchChar);
            System.out.println("isMatch: " + isMatch + ", currentMatchPatternIndex: " + currentMatchPatternIndex +
                     ", currentMatchPattern: " + currentMatchPattern + ", currentSentenceChar: " + currentSentenceChar);
            if(isMatch) {
                currentSentenceIndex = currentSentenceIndex + 1;
                lastMatchPattern = currentMatchPattern;
                lastMatchChar = currentSentenceChar;
                if(currentMatchPattern.length() == 1) {
                    //! a*
                    currentMatchPatternIndex = currentMatchPatternIndex + 1;
                    currentMatchPattern = "";
                }
            } else {
                if(currentMatchPattern.length() == 2) {
                    currentMatchPatternIndex = currentMatchPatternIndex + 2;
                    currentMatchPattern = "";
                } else {
                    return false;
                }
            }
        }
        System.out.println("sentence check done");
        if(currentMatchPattern.length() == 2) {
            currentMatchPatternIndex = currentMatchPatternIndex + 2;
            currentMatchPattern = "";
        }

        while (currentMatchPatternIndex < pattern.length()) {
            int nextMatchPatternIndex = currentMatchPatternIndex + currentMatchPattern.length();
            String nextMatchPattern = pattern.substring(nextMatchPatternIndex, nextMatchPatternIndex + 1);
            String nextMatchChar = nextMatchPattern;
            if((nextMatchPatternIndex + 1) < pattern.length() &&
                    "*".equalsIgnoreCase(pattern.substring(nextMatchPatternIndex + 1, nextMatchPatternIndex + 2))) {
                nextMatchPattern = nextMatchPattern + "*";
                // ex: a*
            }
            System.out.println("nextMatchPatternIndex: " + nextMatchPatternIndex + ", nextMatchPattern: " + nextMatchPattern +
                    ", nextMatchChar = " + nextMatchChar + ", lastMatchChar = " + lastMatchChar + ", lastMatchPattern = " + lastMatchPattern);
            if(nextMatchPattern.length() == 1) {
                if((lastMatchChar.equalsIgnoreCase(nextMatchChar) || nextMatchChar.equalsIgnoreCase(".")) &&
                        lastMatchPattern.length() == 2) {
                    //ex last: a* //next: a
                    currentMatchPatternIndex = nextMatchPatternIndex + nextMatchPattern.length();
                    lastMatchChar = nextMatchChar;
                    lastMatchPattern = nextMatchPattern;
                } else {
                    return false;
                }
            } else {
                currentMatchPatternIndex = nextMatchPatternIndex + nextMatchPattern.length();
            }
            System.out.println("currentMatchPatternIndex: " + currentMatchPatternIndex + ", currentMatchPattern: " + currentMatchPattern);
        }


        return true;
    }

    public static void main(String[] args) {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        System.out.println(regularExpressionMatching.isMatch("a", ".*..a*"));
//        System.out.println(regularExpressionMatching.isMatch("ab", ".*.."));
//        System.out.println(regularExpressionMatching.isMatch("bbbba", ".*a*a"));
//        System.out.println(regularExpressionMatching.isMatch("ab", ".*"));
//        System.out.println(regularExpressionMatching.isMatch("aaba", "ab*a*c*a"));
    }
}
