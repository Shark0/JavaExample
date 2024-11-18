package com.shark.example.algorithm;

import com.google.gson.Gson;

import java.util.*;

public class DiceCombinationExample {

    private void solution2(int dice[], int count) {
        List<List<Integer>> currentResults = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<List<Integer>> tempResults = new ArrayList<>();

            for (int diceNum : dice) {
                if (i == 0) {
                    List<Integer> result = new ArrayList<>();
                    result.add(diceNum);
                    tempResults.add(result);
                } else {
                    for (List<Integer> result : currentResults) {
                        int lastResultNum = result.get(result.size() - 1);
                        if(lastResultNum > diceNum) {
                            continue;
                        }
                        List<Integer> tempResult = new ArrayList<>(result);
                        tempResult.add(diceNum);
                        tempResults.add(tempResult);
                    }
                }
            }
            currentResults = tempResults;
        }
        System.out.println(currentResults.size());
    }


    public void solution1(int[] dice, int count) {
        List<List<Integer>> currentResults = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < count; i++) {
            List<List<Integer>> tempResults = new ArrayList<>();

            for (int diceNum : dice) {
                if (i == 0) {
                    List<Integer> result = new ArrayList<>();
                    result.add(diceNum);
                    tempResults.add(result);
                } else {
                    for (List<Integer> result : currentResults) {
                        List<Integer> tempResult = new ArrayList<>(result);
                        tempResult.add(diceNum);
                        tempResults.add(tempResult);

                    }
                }
            }
            currentResults = tempResults;
        }

        Set<String> set = new HashSet<>();
        for (List<Integer> result : currentResults) {
            Collections.sort(result);
            String json = gson.toJson(result);
            set.add(json);
        }
        System.out.println(set.size());
    }

    public static void main(String[] args) {
        DiceCombinationExample diceCombinationExample = new DiceCombinationExample();
        int[] dice = new int[]{1, 2, 3, 4, 5, 6};
        int count = 6;
        diceCombinationExample.solution1(dice, count);
        diceCombinationExample.solution2(dice, count);
    }
}
