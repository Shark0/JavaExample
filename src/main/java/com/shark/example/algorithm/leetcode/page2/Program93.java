package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<>();
        if(s.length() < 3) {
            return results;
        }

        for(int point1 = 1; point1 <= 3; point1++) {
            String point1SubString = s.substring(0, point1);
            if(point1SubString.charAt(0) == '0' && point1SubString.length() > 1 ) {
                break;
            }
            int point1Value = Integer.parseInt(point1SubString);
            if(point1Value > 255) {
                break;
            }
            for(int j = 1; j <= 3; j ++) {
                int point2 = point1 + j;
                if(point2 >= s.length()) {
                    break;
                }
                String point2SubString = s.substring(point1, point2);
                if(point2SubString.charAt(0) == '0' && point2SubString.length() > 1) {
                    break;
                }
                int point2Value = Integer.parseInt(point2SubString);
                if(point2Value > 255) {
                    break;
                }
                for(int k = 1; k <= 3; k ++) {
                    int point3 = point2 + k;
                    if(point3 >= s.length()) {
                        break;
                    }
                    if( (s.length() - point3) > 3) {
                        continue;
                    }
                    String point3SubString = s.substring(point2, point3);
                    if(point3SubString.charAt(0) == '0' && point3SubString.length() > 1) {
                        break;
                    }
                    int point3Value = Integer.parseInt(point3SubString);
                    if(point3Value > 255) {
                        break;
                    }

                    String point4SubString = s.substring(point3);
                    if(point4SubString.length() > 1 && point4SubString.charAt(0) == '0') {
                        continue;
                    }
                    int point4Value = Integer.parseInt(point4SubString);
                    if(point4Value > 255) {
                        continue;
                    }
                    String result = point1Value + "." + point2Value + "." + point3Value + "." + point4Value;
                    results.add(result);
                }
            }
        }
        return results;
    }

    public static void main(String[] argv) {
        String s = "101023";
        List<String> results = new Program93().restoreIpAddresses(s);
        System.out.println(new Gson().toJson(results));
    }
}
