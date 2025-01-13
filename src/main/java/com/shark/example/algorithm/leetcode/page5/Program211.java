package com.shark.example.algorithm.leetcode.page5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

public class Program211 {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        wordDictionary.addWord("ab");
        System.out.println(new Gson().toJson(wordDictionary.root));
        System.out.println(wordDictionary.search("a."));
    }

    public static class WordDictionary {

        private final Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node child = current.childrenMap.get(c);
                if (child == null) {
                    child = new Node();
                    current.childrenMap.put(c, child);
                }
                current = child;
            }
            current.isLeaf = true;
        }

        public boolean search(String word) {
            //bfs
            List<Node> currentList = List.of(root);
            for (int i = 0; i < word.length(); i++) {
                List<Node> tempList = new ArrayList<>();
                char c = word.charAt(i);
                if(c == '.') {
                    for (Node node : currentList) {
                        tempList.addAll(node.childrenMap.values());
                    }
                } else {
                    for (Node node : currentList) {
                        Node child = node.childrenMap.get(c);
                        if(child != null) {
                            tempList.add(child);
                        }
                    }
                }
                if(tempList.isEmpty()) {
                    return false;
                }
                currentList = tempList;
            }

            for (Node node : currentList) {
                if(node.isLeaf) {
                    return true;
                }
            }
            return false;
        }

        public static class Node {
            boolean isLeaf = false;
            public Map<Character, Node> childrenMap = new HashMap<Character, Node>();
        }
    }
}
