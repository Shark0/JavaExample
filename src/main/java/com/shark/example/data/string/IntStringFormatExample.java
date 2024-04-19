package com.shark.example.data.string;

import java.util.Random;

public class IntStringFormatExample {
    public static void main(String[] argv) {
        Random random = new Random();
        for(int i = 0; i< 100; i ++) {
            String verificationCode= String.format("%06d", random.nextInt(999999));
            System.out.println("verificationCode: " + verificationCode);
        }
    }
}
