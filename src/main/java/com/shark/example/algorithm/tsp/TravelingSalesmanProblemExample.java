package com.shark.example.algorithm.tsp;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TravelingSalesmanProblemExample {

    public static void main(String[] argv) {
        TravelingSalesmanProblem travelingSalesmanProblem = new TravelingSalesmanProblem();
        int[][] map = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };
        boolean[] visitedPositionNumber = new boolean[map.length];
        visitedPositionNumber[0] = true;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        TspResult tspResult = new TspResult();
        tspResult.setMinCost(Integer.MAX_VALUE);
        tspResult = travelingSalesmanProblem.solve(map, visitedPositionNumber, path, 0, 1, 0, tspResult);
        System.out.println("tspResult: " + new Gson().toJson(tspResult));
    }
}
