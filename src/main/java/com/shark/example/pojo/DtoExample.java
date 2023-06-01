package com.shark.example.pojo;

import com.google.gson.Gson;

public class DtoExample {
    public static void main(String[] argv) {
        TestDto testDto = new TestDto();
        System.out.println(testDto.isBooleanValue());
        Gson gson = new Gson();
        System.out.println(gson.toJson(testDto));
    }
}
