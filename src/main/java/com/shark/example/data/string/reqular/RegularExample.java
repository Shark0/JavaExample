package com.shark.example.data.string.reqular;

public class RegularExample {
    public static void main(String[] argv) {
        String value = "if (player.IsAI)//机器人";
        System.out.println(value.matches("[\\w\\s]+\\w+\\(.*\\)"));
    }
}
