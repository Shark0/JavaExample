package com.shark.example.algorithm.leetcode.page9;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program443 {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        //group
        List<Character> charList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        Character preChar = null;
        int preCount = 0;
        for (char c : chars) {
            if (preChar == null || preChar != c) {
                if (preChar != null) {
                    charList.add(preChar);
                    countList.add(preCount);
                }
                preChar = c;
                preCount = 1;
            } else {
                preCount++;
            }
        }
        charList.add(preChar);
        countList.add(preCount);
        //generate result
        int result = 0;
        int index = 0;
        for(int i = 0; i < charList.size(); i++) {
            char c = charList.get(i);
            result = result + 1;
            chars[index] = c;
            index = index + 1;
            int count = countList.get(i);
            if(count != 1) {
                List<Character> countCharList = new ArrayList<>();
                while (count != 0) {
                    char countChar = (char) (count % 10 + '0');
                    countCharList.add(0, countChar);
                    count = count / 10;
                }
                for (char countChar : countCharList) {
                    chars[index] = countChar;
                    index++;
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        char[] inputs = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        char[] inputs = new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
//        char[] inputs = new char[]{'a', 'b', 'c'};
        Program443 program443 = new Program443();
        System.out.println(program443.compress(inputs));
        System.out.println(new Gson().toJson(inputs));
    }
}
