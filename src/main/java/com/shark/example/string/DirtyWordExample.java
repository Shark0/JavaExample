package com.shark.example.string;

import com.google.gson.Gson;
import com.shark.example.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DirtyWordExample {

    public static void main(String[] argv) {
        List<String> dirtyWordList = List.of("幹你娘", "幹你娘老雞掰", "操你媽的B", "射在牆上");
        Map<String, DirtyWordTreeNode> dirtyWordTreeMap = generateDirtyWordTree(dirtyWordList);
        System.out.println(new Gson().toJson(dirtyWordTreeMap));
        String example = "幹你娘老雞掰，操@你@媽@的@B，你爸怎麼不把你射在牆上";
        example = filterDirtyWord(dirtyWordTreeMap, example);
        System.out.println(example);
    }

    private static Map<String, DirtyWordTreeNode> generateDirtyWordTree(List<String> dirtyWordList) {
        Map<String, DirtyWordTreeNode> map = new HashMap<>();
        for(String dirtyWord: dirtyWordList) {
            Map<String, DirtyWordTreeNode> tempMap = map;
            for(int i = 0; i < dirtyWord.length(); i ++) {
                String charValue = dirtyWord.substring(i, i + 1);
                DirtyWordTreeNode dirtyWordTreeNode = tempMap.get(charValue);
                if(dirtyWordTreeNode == null) {
                    dirtyWordTreeNode = new DirtyWordTreeNode(charValue);
                    tempMap.put(charValue, dirtyWordTreeNode);
                }
                if(i == dirtyWord.length() - 1) {
                    dirtyWordTreeNode.setDirtyWord(dirtyWord);
                }
                tempMap = dirtyWordTreeNode.getChildMap();
            }
        }
        return map;
    }

    private static String filterDirtyWord(Map<String, DirtyWordTreeNode> dirtyWordTreeMap, String word) {
        int startIndex = 0;
        String result = word;
        while(startIndex < result.length()) {
            System.out.println("startIndex: " + startIndex + ", result = " + result);
            int dirtyWordRegion = findDirtyWordRegin(dirtyWordTreeMap, result, startIndex, 0);
            if(dirtyWordRegion != -1) {
                result = replaceDirtyWord(result, startIndex, dirtyWordRegion);
                startIndex = startIndex + dirtyWordRegion;
            } else {
                startIndex ++;
            }
        }
        return result;
    }

    private static String replaceDirtyWord(String result, int startIndex, int region) {
        String dirtyWord = result.substring(startIndex, startIndex + region);
        StringBuilder replaceStringBuilder = new StringBuilder();
        for(int i = 0; i < region; i++) {
            replaceStringBuilder.append("*");
        }
        return result.replace(dirtyWord, replaceStringBuilder.toString());
    }

    private static int findDirtyWordRegin(Map<String, DirtyWordTreeNode> dirtyWordTreeNodeMap, String word, int startIndex, int regionSize) {
        System.out.println("word = " + word + ", startIndex = " + startIndex + ", regionSize = " + regionSize);
        if(startIndex >= word.length()) {
            return -1;
        }
        String charValue = word.substring(startIndex, startIndex + 1);
        boolean isIgnoreChar = isIgnoreChar(charValue);
        if(isIgnoreChar) {
            if((startIndex == word.length() - 1) || regionSize == 0) {
                return -1;
            }
            return findDirtyWordRegin(dirtyWordTreeNodeMap, word, (startIndex + 1), (regionSize + 1));
        } else {
            DirtyWordTreeNode dirtyWordTreeNode = dirtyWordTreeNodeMap.get(charValue);
            if(dirtyWordTreeNode == null) {
                return -1;
            } else {
                String dirtyWord = dirtyWordTreeNode.getDirtyWord();
                if(StringUtil.isEmpty(dirtyWord)) {
                    if(dirtyWordTreeNode.getChildMap().isEmpty()) {
                        return -1;
                    } else {
                        return findDirtyWordRegin(dirtyWordTreeNode.getChildMap(), word, (startIndex + 1), (regionSize + 1));
                    }
                } else {
                    if(dirtyWordTreeNode.getChildMap().isEmpty()) {
                        return regionSize + 1;
                    } else {
                        int childResultRegion = findDirtyWordRegin(dirtyWordTreeNode.getChildMap(), word, (startIndex + 1), (regionSize + 1));
                        if(childResultRegion != -1) {
                            return childResultRegion;
                        }
                        return regionSize + 1;
                    }
                }
            }
        }
    }

    private static boolean isIgnoreChar(String charValue) {
        String regEx = "\\pP|\\pS|\\s+";
        return Pattern.compile(regEx).matcher(charValue).matches();
    }
}
