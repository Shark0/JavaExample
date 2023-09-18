package com.shark.example.string;

import com.google.gson.Gson;
import com.shark.example.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DirtyWordExample {

    public static void main(String[] argv) {
        List<String> dirtyWordList = List.of("幹你娘", "智障", "操你媽", "傻B", "幹", "操", "賤", "婊");
        Map<String, DirtyWordTreeNodeDto> dirtyWordTreeMap = generateDirtyWordTree(dirtyWordList);
        System.out.println(new Gson().toJson(dirtyWordTreeMap));
        String example1 = "幹!你!娘!的智障，操#你#媽的傻B，你爸怎麼不把你射在牆上";

        System.out.println(example1 + ": " + filterDirtyWord(dirtyWordTreeMap, example1));

        String example2 = "幹嘛這樣";
        System.out.println(example2 + ": " + filterDirtyWord(dirtyWordTreeMap, example2));

        String example3 = "幹 嘛這樣";
        System.out.println(example3 + ": " + filterDirtyWord(dirtyWordTreeMap, example3));

        String example4 = "幹操賤婊 ";
        System.out.println(example4 + ": " + filterDirtyWord(dirtyWordTreeMap, example4));

        String example5 = "婊子";
        System.out.println(example5 + ": " + filterDirtyWord(dirtyWordTreeMap, example5));
    }

    private static Map<String, DirtyWordTreeNodeDto> generateDirtyWordTree(List<String> dirtyWordList) {
        Map<String, DirtyWordTreeNodeDto> map = new HashMap<>();
        for(String dirtyWord: dirtyWordList) {
            Map<String, DirtyWordTreeNodeDto> tempMap = map;
            for(int i = 0; i < dirtyWord.length(); i ++) {
                String charValue = dirtyWord.substring(i, i + 1);
                DirtyWordTreeNodeDto dirtyWordTreeNodeDto = tempMap.get(charValue);
                if(dirtyWordTreeNodeDto == null) {
                    dirtyWordTreeNodeDto = new DirtyWordTreeNodeDto(charValue);
                    tempMap.put(charValue, dirtyWordTreeNodeDto);
                }
                if(i == dirtyWord.length() - 1) {
                    dirtyWordTreeNodeDto.setDirtyWord(dirtyWord);
                }
                tempMap = dirtyWordTreeNodeDto.getChildMap();
            }
        }
        return map;
    }

    private static String filterDirtyWord(Map<String, DirtyWordTreeNodeDto> dirtyWordTreeMap, String sentence) {
        int startIndex = 0;
        String result = sentence;

        List<DirtyWordScopeDto> dirtyWordScopeDtoList = new ArrayList<>();
        while(startIndex < result.length()) {
//            System.out.println("startIndex: " + startIndex + ", result = " + result);
            int dirtyWordScope = findDirtyWordScope(dirtyWordTreeMap, result, startIndex, 0);
            if(dirtyWordScope != -1) {
                DirtyWordScopeDto dirtyWordScopeDto = new DirtyWordScopeDto();
                dirtyWordScopeDto.setStartIndex(startIndex);
                dirtyWordScopeDto.setScope(dirtyWordScope);
                dirtyWordScopeDtoList.add(dirtyWordScopeDto);
                startIndex = startIndex + dirtyWordScope;
            } else {
                startIndex ++;
            }
        }

        for(int i = 0 ; i < dirtyWordScopeDtoList.size(); i ++) {
            DirtyWordScopeDto dirtyWordScopeDto = dirtyWordScopeDtoList.get(i);
            if(dirtyWordScopeDto.getScope() == 1) {
                if(i == dirtyWordScopeDtoList.size() - 1) {
                    boolean isLastWord = (dirtyWordScopeDto.getStartIndex() + dirtyWordScopeDto.getScope() == sentence.length());
                    if(isLastWord) {
                        //last word
                        result = replaceDirtyWord(result, dirtyWordScopeDto.getStartIndex(), dirtyWordScopeDto.getScope());
                    } else {
                        int nextCharStartIndex = (dirtyWordScopeDto.getStartIndex() + dirtyWordScopeDto.getScope());
                        String nextChar = sentence.substring(nextCharStartIndex, nextCharStartIndex + 1);
                        if(isIgnoreChar(nextChar)) {
                            result = replaceDirtyWord(result, dirtyWordScopeDto.getStartIndex(), dirtyWordScopeDto.getScope());
                        }
                    }
                } else {
                    DirtyWordScopeDto nextDirtyWordScopeDto = dirtyWordScopeDtoList.get(i + 1);
                    if(nextDirtyWordScopeDto.getStartIndex() == (dirtyWordScopeDto.getStartIndex() + dirtyWordScopeDto.getScope())) {
                        result = replaceDirtyWord(result, dirtyWordScopeDto.getStartIndex(), dirtyWordScopeDto.getScope());
                    } else {
                        int nextCharStartIndex = (dirtyWordScopeDto.getStartIndex() + dirtyWordScopeDto.getScope());
                        String nextChar = sentence.substring(nextCharStartIndex, nextCharStartIndex + 1);
                        if(isIgnoreChar(nextChar)) {
                            result = replaceDirtyWord(result, dirtyWordScopeDto.getStartIndex(), dirtyWordScopeDto.getScope());
                        }
                    }
                }
            } else {
                result = replaceDirtyWord(result, dirtyWordScopeDto.getStartIndex(), dirtyWordScopeDto.getScope());
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

    private static int findDirtyWordScope(Map<String, DirtyWordTreeNodeDto> dirtyWordTreeNodeMap, String word, int startIndex, int scope) {
//        System.out.println("word = " + word + ", startIndex = " + startIndex + ", scope = " + scope);
        if(startIndex >= word.length()) {
            return -1;
        }
        String charValue = word.substring(startIndex, startIndex + 1);
        boolean isIgnoreChar = isIgnoreChar(charValue);
        if(isIgnoreChar) {
            if((startIndex == word.length() - 1) || scope == 0) {
                return -1;
            }
            return findDirtyWordScope(dirtyWordTreeNodeMap, word, (startIndex + 1), (scope + 1));
        } else {
            DirtyWordTreeNodeDto dirtyWordTreeNodeDto = dirtyWordTreeNodeMap.get(charValue);
            if(dirtyWordTreeNodeDto == null) {
                return -1;
            } else {
                String dirtyWord = dirtyWordTreeNodeDto.getDirtyWord();
                if(StringUtil.isEmpty(dirtyWord)) {
                    if(dirtyWordTreeNodeDto.getChildMap().isEmpty()) {
                        return -1;
                    } else {
                        return findDirtyWordScope(dirtyWordTreeNodeDto.getChildMap(), word, (startIndex + 1), (scope + 1));
                    }
                } else {
                    if(dirtyWordTreeNodeDto.getChildMap().isEmpty()) {
                        return scope + 1;
                    } else {
                        int childResultRegion = findDirtyWordScope(dirtyWordTreeNodeDto.getChildMap(), word, (startIndex + 1), (scope + 1));
                        if(childResultRegion != -1) {
                            return childResultRegion;
                        }
                        return scope + 1;
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
