package com.shark.example.algorithm.leetcode.page15;

import com.google.gson.Gson;

public class Program735 {

    public int[] asteroidCollision(int[] asteroids) {

        int[] tempResults = new int[asteroids.length];
        int tempResultSize = 0;
        for (int value : asteroids) {
            if (value >= 0) {
                tempResults[tempResultSize] = value;
                tempResultSize++;
            } else {
                while (tempResultSize >= 0) {
                    if (tempResultSize == 0) {
                        tempResults[0] = value;
                        tempResultSize++;
                        break;
                    }

                    int previewValue = tempResults[tempResultSize - 1];
                    boolean isPreviewValuePositive = previewValue >= 0;
                    if (isPreviewValuePositive) {
                        if (previewValue == -value) {
                            tempResultSize--;
                            break;
                        } else if (previewValue < -value) {
                            tempResultSize--;
                        } else {
                            break;
                        }
                    } else {
                        tempResults[tempResultSize] = value;
                        tempResultSize++;
                        break;
                    }
                }
            }


            System.out.println("value: " + value + ", tempResultSize: " + tempResultSize +
                    ", tempResults: " + new Gson().toJson(tempResults));
        }

        int[] results = new int[tempResultSize];
        System.arraycopy(tempResults, 0, results, 0, tempResultSize);
        return results;
    }

    public static void main(String[] args) {
        Program735 program735 = new Program735();
        int[] asteroids = new int[]{-2,-1,1,2};
        int[] result = program735.asteroidCollision(asteroids);
        System.out.println("result: " + new Gson().toJson(result));
    }

}
