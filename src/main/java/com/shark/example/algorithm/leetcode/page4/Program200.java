package com.shark.example.algorithm.leetcode.page4;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Program200 {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0' },
                {'1', '1', '0', '1', '0' },
                {'1', '1', '0', '0', '0' },
                {'0', '0', '0', '0', '0' }
        };
        char[][] grid1 = new char[][]{
                {'1', '1', '0', '0', '0' },
                {'1', '1', '0', '0', '0' },
                {'1', '0', '1', '0', '0' },
                {'1', '1', '0', '1', '1' }
        };
        System.out.println(new Program200().numIslands(grid1));
    }

    public int numIslands(char[][] grid) {
        int island = 0;
        Set<Integer> islandSet = new HashSet<>();
        Map<String, Integer> locationIslandMap = new HashMap<>();
        Map<Integer, Set<String>> islandLocationSetMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String location = i + "-" + j;
                char land = grid[i][j];
                if(land == '1') {
                    if(i == 0) {
                        if(j == 0) {
                            island = addIsland(islandSet, locationIslandMap, islandLocationSetMap, location, island);
                        } else {
                            String leftLocation = i + "-" + (j - 1);
                            Integer leftIsland = locationIslandMap.get(leftLocation);
                            if(leftIsland == null) {
                                island = addIsland(islandSet, locationIslandMap, islandLocationSetMap, location, island);
                            } else {
                                locationIslandMap.put(location, leftIsland);
                                islandLocationSetMap.get(leftIsland).add(location);
                            }
                        }
                    } else {
                        //up
                        String upLocation = (i - 1) + "-" + j;
                        Integer upIsland = locationIslandMap.get(upLocation);
                        if(upIsland == null) {
                            if(j > 0) {
                                String leftLocation = i + "-" + (j - 1);
                                Integer leftIsland = locationIslandMap.get(leftLocation);
                                if(leftIsland == null) {
                                    island = addIsland(islandSet, locationIslandMap, islandLocationSetMap, location, island);
                                } else {
                                    locationIslandMap.put(location, leftIsland);
                                    islandLocationSetMap.get(leftIsland).add(location);
                                }
                            } else {
                                island = addIsland(islandSet, locationIslandMap, islandLocationSetMap, location, island);
                            }
                        } else {
                            locationIslandMap.put(location, upIsland);
                            islandLocationSetMap.get(upIsland).add(location);
                        }
                        //left
                        if(j > 0) {
                            String leftLocation = i + "-" + (j - 1);
                            mergeLeft(islandSet, locationIslandMap, islandLocationSetMap, leftLocation, location);
                        }
                    }
                }
            }
        }
        return islandSet.size();
    }

    private void mergeLeft(
            Set<Integer> islandSet,
            Map<String, Integer> locationIslandMap,
            Map<Integer, Set<String>> islandLocationSetMap,
            String leftLocation, String location) {
        Integer leftIsland = locationIslandMap.get(leftLocation);
        if(leftIsland == null) {
            return;
        }
        Integer island = locationIslandMap.get(location);
        if(leftIsland.equals(island)) {
            return;
        }
        Set<String> islandLocationSet = islandLocationSetMap.get(island);
        Set<String> leftIslandLocationSet = islandLocationSetMap.get(leftIsland);
        leftIslandLocationSet.addAll(islandLocationSet);
        for (String islandLocation : islandLocationSet) {
            locationIslandMap.put(islandLocation, leftIsland);
        }
        islandSet.remove(island);
        islandLocationSetMap.remove(island);
    }

    public int addIsland(
            Set<Integer> islandSet,
            Map<String, Integer> locationIslandMap,
            Map<Integer, Set<String>> islandLocationSetMap,
            String location, Integer island) {
        islandSet.add(island);
        Set<String> locationSet = new HashSet<>();
        locationSet.add(location);
        islandLocationSetMap.put(island, locationSet);
        locationIslandMap.put(location, island);
        return island + 1;
    }
}
