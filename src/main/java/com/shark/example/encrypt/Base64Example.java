package com.shark.example.encrypt;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Example {
    public static void main(String argv[]) {
        String message = "message";
        String base64 = Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
        System.out.println("base64: " + base64);
        message = new String(Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        System.out.println("message: " + message);
    }
}
