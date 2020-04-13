package com.shark.example.random;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomExample {
    public static void main(String[] argv) {
        int randomCount = 293;
        int randomSize = (randomCount / 10) + 1;
        int[] randomValues = new Random().ints(1, randomCount).distinct().limit(randomSize).toArray();
        List<Integer> randomList = Arrays.stream(randomValues).boxed().sorted().collect(Collectors.toList());
        System.out.println(new Gson().toJson(randomList));
    }
}
