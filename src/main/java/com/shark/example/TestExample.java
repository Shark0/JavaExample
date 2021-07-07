package com.shark.example;

public class TestExample {
    public static void main(String argv[]) {
        Integer value = 5;
        value = test(value);
        System.out.println(value);
    }

    private static Integer test(Integer value) {
        return value * value;
    }

}

