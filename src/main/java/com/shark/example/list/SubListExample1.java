package com.shark.example.list;

import java.util.ArrayList;
import java.util.List;

public class SubListExample1 {
    public static void main(String argv[]) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        List<String> list1 = new ArrayList<>();
        list1.addAll(list);
        list1.remove("10");

        System.out.println("list size: " + list.size());
        System.out.println("list1 size: " + list1.size());
    }
}
