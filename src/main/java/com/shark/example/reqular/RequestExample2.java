package com.shark.example.reqular;

import java.util.regex.Pattern;

public class RequestExample2 {
    public static void main(String[] argv) {
        String text1 = "幹、你、娘";
        System.out.println(removeSpecialChar(text1));
        String text2 = "f1u2c3k4y5o6u7";
        System.out.println(removeSpecialChar(text2));
    }


    public static String removeSpecialChar(String value) {
        String regEx = "\\pP|\\pS|\\s+";
        value = Pattern.compile(regEx).matcher(value).replaceAll("").trim();
        return value;
    }
}
