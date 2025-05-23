package com.shark.example.algorithm.leetcode.page16;

import com.google.gson.Gson;

import java.util.*;

public class Program763 {


    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> charLastIndexSet = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charLastIndexSet.put(s.charAt(i), i);
        }
        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, charLastIndexSet.get(s.charAt(i)));
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }


    public List<Integer> partitionLabelsTemp1(String s) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return result;
        }
        //init
        Stack<Set<Character>> charSetStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        Set<Character> currentCharSet = new HashSet<>();
        currentCharSet.add(s.charAt(0));
        int currentCount = 1;
        //handle
        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
//            System.out.println("char: " + c + ", currentCharSet: " + new Gson().toJson(currentCharSet) +
//                    ", currentCount: " + currentCount + ", charSetStack: " +
//                    new Gson().toJson(charSetStack) + ", countStack: " + new Gson().toJson(countStack));

            if(currentCharSet.contains(c)) {
                currentCount ++;
            } else  {
                Stack<Set<Character>> tempCharSetStack = new Stack<>();
                Stack<Integer> tempCountStack = new Stack<>();
                Set<Character> tempSet = new HashSet<>(currentCharSet);
                int tempCount = currentCount;
                boolean contains = false;
                while(!charSetStack.isEmpty()) {
                    Set<Character> previewSet = charSetStack.pop();
                    tempCharSetStack.push(previewSet);
                    tempSet.addAll(previewSet);
                    Integer previewCount = countStack.pop();
                    tempCountStack.push(previewCount);
                    tempCount = tempCount + previewCount;
                    if(previewSet.contains(c)) {
                        contains = true;
                        break;
                    }
                }
                if(contains) {
                    currentCharSet = tempSet;
                    currentCount= tempCount + 1;
                } else {
                    while (!tempCharSetStack.isEmpty()) {
                        Set<Character> previewCharSet = tempCharSetStack.pop();
                        charSetStack.push(previewCharSet);
                        Integer previewCount = tempCountStack.pop();
                        countStack.push(previewCount);
                    }
                    charSetStack.push(currentCharSet);
                    countStack.push(currentCount);
                    currentCharSet = new HashSet<>();
                    currentCharSet.add(c);
                    currentCount = 1;
                }
            }
        }
        result.add(currentCount);
        while(!countStack.isEmpty()) {
            result.addFirst(countStack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new Program763().partitionLabels("qiejxqfnqceocmy");
        System.out.println("result: " + new Gson().toJson(result));
    }

}
