package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.*;

public class Program139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        int textLength = s.length();
        boolean[] dp = new boolean[textLength + 1];
        dp[0] = true;
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }
        for (int i = 1; i <= textLength; i++) {
            for (int j = i - 1; j >= Math.max(i - maxLength - 1, 0); j--) {
                if(wordDict.contains(s.substring(j, i))) {
                    String word = s.substring(j, i);
                    System.out.println("i = " + i + ", j = " + j + ", word = " + word + ", dp = " + Arrays.toString(dp));
                }
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[textLength];
    }

    public boolean wordBreak1(String text, List<String> wordDict) {
        Stack<Integer> wordDictIndexStack = new Stack<>();
        int index = 0;
        int wordIndex = 0;
        int wordCharIndex = 0;
        String word = wordDict.get(wordIndex);
        while (index < text.length()) {
            char textChar = text.charAt(index);
            char wordChar = word.charAt(wordCharIndex);
            System.out.println("index: " + index + ", wordIndex: " + wordIndex + ", word: " + word + ", wordCharIndex: " + wordCharIndex +
                    ", textChar: " + textChar + ", wordChar: " + wordChar + ", queue: " + new Gson().toJson(wordDictIndexStack));

            boolean isMatch = (textChar == wordChar);
            if (isMatch) {
                if (wordCharIndex == word.length() - 1) {
                    wordDictIndexStack.add(wordIndex);
                    wordIndex = 0;
                    wordCharIndex = 0;
                    word = wordDict.get(wordIndex);
                    index++;
                } else {

                    if(index == text.length() - 1) {
                        isMatch = false;
                    } else {
                        wordCharIndex++;
                        index++;
                    }
                }
            }

            if(!isMatch) {
                index = index - wordCharIndex - 1;
                if (wordIndex == wordDict.size() - 1) {
                    if (wordDictIndexStack.isEmpty()) {
                        return false;
                    } else {
                        wordIndex = wordDictIndexStack.pop(); //back to preview index;
                        while(wordIndex  == wordDict.size() - 1) {
                            if(wordDictIndexStack.isEmpty()) {
                                return false;
                            } else {
                                index = index - wordDict.get(wordIndex).length();
                                wordIndex = wordDictIndexStack.pop();
                            }
                        }
                        index = index - wordDict.get(wordIndex).length();
                        wordIndex = wordIndex + 1;
                        wordCharIndex = 0;
                        word = wordDict.get(wordIndex);
                        index++;
                    }
                } else {
                    wordIndex = wordIndex + 1; //change to next word index
                    wordCharIndex = 0;
                    word = wordDict.get(wordIndex);
                    index++;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aaaaaaa";
        List<String> wordDict = Arrays.asList("aaaa", "aa");
        System.out.println(new Program139().wordBreak(s, wordDict));
    }

}
