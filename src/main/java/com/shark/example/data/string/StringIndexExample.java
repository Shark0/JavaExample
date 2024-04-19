package com.shark.example.data.string;

public class StringIndexExample {
    public static void main(String[] argv) {
        String example1 = "123456789";
        int index1 = example1.indexOf("4567");
        System.out.println("index1 = " + index1);
        String example2 = "000000000";



        int index2 = example2.indexOf("4567");
        System.out.println("index2 = " + index2);
    }
}
