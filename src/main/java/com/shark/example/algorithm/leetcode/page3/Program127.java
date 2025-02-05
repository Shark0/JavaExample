package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.*;

public class Program127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //init
        int wordCount = 1;
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Map<String, char[]> wordCharsMap = generateWordCharsMap(beginWord, wordList);
        Map<String, Set<String>> graph = generateGraph(beginWord, wordList, wordCharsMap);
//        System.out.println(new Gson().toJson(graph));
        Set<String> checkedWordSet = new HashSet<>();
        List<String> checkWordList = List.of(beginWord);

        //start bfs
        while (!checkWordList.isEmpty()) {
            List<String> nextCheckWordList = new ArrayList<>();
            for (String checkWord : checkWordList) {
//                System.out.println("checkWord: " + checkWord);
                Set<String> childWordSet = graph.get(checkWord);
                for (String child : childWordSet) {
                    if(child.equals(endWord)) {
                        return wordCount + 1;
                    }
                    if (checkedWordSet.contains(child)) {
                        continue;
                    }
                    checkedWordSet.add(child);
                    nextCheckWordList.add(child);
                }
            }
            wordCount++;
            checkWordList = nextCheckWordList;
        }
        return 0;
    }

    public Map<String, char[]> generateWordCharsMap(String beginWord, List<String> wordList) {
        Map<String, char[]> map = new HashMap<>();
        map.put(beginWord, beginWord.toCharArray());
        for (String word : wordList) {
            map.put(word, word.toCharArray());
        }
        return map;
    }

    private Map<String, Set<String>> generateGraph(
            String beginWord, List<String> wordList,
            Map<String, char[]> wordCharsMap) {
        Map<String, Set<String>> graph = new HashMap<>();
        char[] beginWordChars = wordCharsMap.get(beginWord);
        Set<String> beginWordChildSet = new HashSet<>();
        for(String word : wordList) {
            char[] wordChars = wordCharsMap.get(word);
            int differentCount = 0;
            for(int index = 0; index < wordChars.length; index++) {
                char c1 = beginWordChars[index];
                char c2 = wordChars[index];
                if(c1 != c2) {
                    differentCount++;
                    if(differentCount > 1) {
                        break;
                    }
                }
            }
            if(differentCount == 1) {
                beginWordChildSet.add(word);
            }
        }
        graph.put(beginWord, beginWordChildSet);

        for(int i = 0; i < wordList.size(); i++) {
            String word1 = wordList.get(i);
            char[] word1Chars = wordCharsMap.get(word1);
            for(int j = 1; j < wordList.size(); j++) {
                String word2 = wordList.get(j);
                char[] word2Chars = wordCharsMap.get(word2);
                int differentCount = 0;
                for(int index = 0; index < word1Chars.length; index++) {
                    char c1 = word1Chars[index];
                    char c2 = word2Chars[index];
                    if(c1 != c2) {
                        differentCount++;
                        if(differentCount > 1) {
                            break;
                        }
                    }
                }
                if(differentCount == 1) {
                    Set<String> word1ChildSet = graph.getOrDefault(word1, new HashSet<>());
                    word1ChildSet.add(word2);
                    graph.put(word1, word1ChildSet);
                    Set<String> word2ChildSet = graph.getOrDefault(word2, new HashSet<>());
                    word2ChildSet.add(word1);
                    graph.put(word2, word2ChildSet);
                }
            }
        }

        return graph;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("word count: " + new Program127().ladderLength(beginWord, endWord, wordList));
    }
}
