package com.shark.example.algorithm.tsp;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TravelingSalesmanProblem {
    public TspResult solve(int[][]map, boolean[] visitedPosition, List<Integer> path,
                   int currentPosition, int visitedPositionCount, int cost, TspResult tspResult) {
        int positionCount = map.length;
        if(positionCount == visitedPositionCount && map[currentPosition][0] > 0) {
            int currentCost = cost + map[currentPosition][0];
            Gson gson = new Gson();
            System.out.println("current path: " + gson.toJson(path));
            System.out.println("current cost: " + currentCost);

            if(currentCost < tspResult.getMinCost()) {
                tspResult.setMinCost(currentCost);
                tspResult.setPath(path);
            }
            return tspResult;
        }

        for(int i = 0; i < positionCount; i++) {
            if (!visitedPosition[i] && map[currentPosition][i] > 0) {
                visitedPosition[i] = true;
                List<Integer> tempPath = new ArrayList<>(path);
                tempPath.add(i);
                tspResult = solve(map, visitedPosition, tempPath, i,   visitedPositionCount + 1,
                        cost + map[currentPosition][i], tspResult);
                visitedPosition[i] = false;
            }
        }
        return tspResult;
    }
}
