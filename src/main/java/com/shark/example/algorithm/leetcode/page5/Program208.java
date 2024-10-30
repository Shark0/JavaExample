package com.shark.example.algorithm.leetcode.page5;

import java.util.HashMap;
import java.util.Map;

public class Program208 {
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("search apple: " + trie.search("apple"));
        System.out.println("search app: " + trie.search("app"));
        System.out.println("start with app: " + trie.startsWith("app"));
        trie.insert("app");
        System.out.println("search app: " + trie.search("app"));
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

        public boolean search(String word) {
            if(word.isEmpty()) {
                return isWord;
            }
            Trie trie = children.get(word.charAt(0));
            if(trie == null) {
                return false;
            }
            return trie.search(word.substring(1));
        }

        public boolean startsWith(String prefix) {
            if(prefix.isEmpty()) {
                return true;
            }
            Trie trie = children.get(prefix.charAt(0));
            if(trie == null) {
                return false;
            }
            return trie.startsWith(prefix.substring(1));
        }
    }
}
