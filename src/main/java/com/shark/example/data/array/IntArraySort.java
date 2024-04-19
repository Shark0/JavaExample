package com.shark.example.data.array;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Comparator;

public class IntArraySort {
    public static void main(String[] argv) {
        Integer[] exampleArray = {1, 2, 3, 4, 5};
        Arrays.sort(exampleArray, Comparator.reverseOrder());
        System.out.println(new Gson().toJson(exampleArray));
    }
}
