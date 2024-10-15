package com.shark.example.algorithm.leetcode.page1.program43;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program43 {

    public String multiply(String num1, String num2) {
        if("0".equalsIgnoreCase(num1) || "0".equalsIgnoreCase(num2)) {
            return "0";
        }
        Gson gson = new Gson();
        List<Integer> resultList = new ArrayList<>();
        int offset = 0;
        for(int i = 0; i < num1.length(); i++) {
            for(int j = 0; j < num2.length(); j++) {
                int resultIndex = i + j + offset;
                Integer originalValue = 0;
                if(resultIndex < resultList.size()) {
                    originalValue = resultList.get(resultIndex);
                }
                int value1 = num1.charAt(i) - '0';
                int value2 = num2.charAt(j) - '0';
                int temp = originalValue + value1 * value2;
                System.out.println("resultList: " + gson.toJson(resultList));
                System.out.println("i: " + i + ", value1: " + value1 +
                        ", j: " + j + ", value2: " + value2 +
                        ", offset: " + offset + ", resultIndex: " + resultIndex +
                        ", originalValue: " + originalValue + ", temp: " + temp);
                if(temp >= 10) {
                    while (temp >= 10) {
                        int tempResult1 = temp / 10;
                        int tempResult2 = temp % 10;
                        System.out.println("temp: " + temp + ", tempResult1: " + tempResult1 + ", tempResult2: " + tempResult2);
                        if(resultIndex < resultList.size()) {
                            resultList.remove(resultIndex);
                            resultList.add(resultIndex, tempResult2);
                        } else {
                            resultList.add(tempResult2);
                        }
                        System.out.println("resultList: " + new Gson().toJson(resultList));

                        if(resultIndex == 0) {
                            resultList.add(0, tempResult1);
                            offset = offset + 1;
                            temp = 0;
                        } else {
                            resultIndex = resultIndex - 1;
                            originalValue = resultList.get(resultIndex);
                            temp = originalValue  + tempResult1;
                            System.out.println("temp: " + temp);
                            if(temp < 10) {
                                resultList.remove(resultIndex);
                                resultList.add(resultIndex, temp);
                            }
                        }
                    }
                } else {
                    if(resultIndex < resultList.size()) {
                        resultList.remove(resultIndex);
                        resultList.add(resultIndex, temp);
                    } else {
                        resultList.add(temp);
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(Integer value : resultList) {
            stringBuilder.append(value);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Program43 program43 = new Program43();
        String result = program43.multiply("12", "5");
        System.out.println("result: " + result);
    }
}
