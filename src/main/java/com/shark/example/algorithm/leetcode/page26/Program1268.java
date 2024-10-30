package com.shark.example.algorithm.leetcode.page26;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program1268 {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        for (String product : products) {
            trie.insert(product);
        }
        System.out.println("tire: " + new Gson().toJson(trie));
        List<List<String>> resultList = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            String subWord = searchWord.substring(0, i + 1);
            Trie resultTrie = trie.searchWord(subWord);
            if(resultTrie == null) {
                resultList.add(List.of());
                continue;
            }
            List<String> result = generateResult(subWord, resultTrie);
            result.sort(String::compareTo);
            System.out.println("subWord: " + subWord +  ", result: " + new Gson().toJson(result));
            if(result.size() < 4) {
                resultList.add(result);
            } else {
                List<String> tempResult = new ArrayList<>();
                for(int j = 0; j < 3; j++) {
                    tempResult.add(result.get(j));
                }
                resultList.add(tempResult);
            }

        }
        return resultList;
    }

    private List<String> generateResult(String keyword, Trie trie) {
        List<String> resultList = new ArrayList<>();
        if(trie.isWord) {
            resultList.add(keyword);
        }

        for(Character c : trie.children.keySet()) {
            String childKeyWord = keyword + c;
            List<String> childResultList = generateResult(childKeyWord, trie.children.get(c));
            resultList.addAll(childResultList);
        }
        return resultList;
    }

    public static void main(String[] args) {
        String[] products = new String[]{"havana"};
        String keyword = "tatiana";
        System.out.println("result: " +
                new Gson().toJson(new Program1268().suggestedProducts(products, keyword)));
    }

    public static class Trie {

        private final Map<Character, Trie> children;
        private boolean isWord;

        public Trie() {
            children = new HashMap<>();
            isWord = false;
        }

        public void insert(String word) {
            if (word.isEmpty()) {
                isWord = true;
                return;
            }
            Character character = word.charAt(0);
            Trie trie = children.get(character);
            if (trie == null) {
                trie = new Trie();
            }
            children.put(character, trie);
            trie.insert(word.substring(1));
        }



        public Trie searchWord(String prefix) {
            if(prefix.isEmpty()) {
                return this;
            }


            Trie trie = children.get(prefix.charAt(0));
            if(trie == null) {
                return null;
            }
            return trie.searchWord(prefix.substring(1));

        }
    }
}
