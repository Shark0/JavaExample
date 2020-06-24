package com.shark.example.equal;

public class EqualExample {
    public static void main(String[] argv) {
        Long value1 = 123123123123123123L;
        Long value2 = 123123123123123123L;
        System.out.println(value1 == value2);
        System.out.println(value1.equals(value2));
        System.out.println(value1.longValue() == value2.longValue());
    }
}
