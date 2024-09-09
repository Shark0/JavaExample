package com.shark.example.algorithm.leetcode.program12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerToRoman {

    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> romainIntValueList = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
        List<String> romainIntTextList = List.of("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        Map<Integer, Integer> valueCountMap = new HashMap<>();
        int remainder = num;
        for (Integer value : romainIntValueList) {
            Integer count = remainder / value;
            valueCountMap.put(value, count);
            remainder = remainder % value;
        }
        for (int i = 0; i < romainIntTextList.size(); i++) {
            Integer value = romainIntValueList.get(i);
            Integer count = valueCountMap.get(value);
            String valueText = romainIntTextList.get(i);
            stringBuilder.append(valueText.repeat(count));
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        IntegerToRoman intToRoman = new IntegerToRoman();
        System.out.println(intToRoman.intToRoman(3479));
    }
}
