package com.shark.example.algorithm.tsp;

import lombok.Data;

import java.util.List;

@Data
public class TspResult {
    private List<Integer> path;
    private int minCost;
}
