package com.shark.example.reqular;

public class RegularExample {
    public static void main(String[] argv) {
        String value = "if (player.IsAI)//机器人";
        System.out.println(value.matches("[\\w\\s]+\\w+\\(.*\\)"));
    }
}
