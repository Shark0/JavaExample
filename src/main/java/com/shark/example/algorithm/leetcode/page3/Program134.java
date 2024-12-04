package com.shark.example.algorithm.leetcode.page3;

public class Program134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int stationCount = gas.length;
        int currentStationCount = 0;
        int startStationIndex = 0;
        int currentTotalGas = 0;
        while (startStationIndex < stationCount) {
            if(currentStationCount == stationCount) {
                return startStationIndex;
            }
            int currentStationIndex = (startStationIndex + currentStationCount) % stationCount;
            int currentGas = gas[currentStationIndex];
            int currentCost = cost[currentStationIndex];
            System.out.println("========================");
            System.out.println("startStationIndex: " + startStationIndex +
                    ", currentStationCount: " + currentStationCount + ", currentStationIndex: " + currentStationIndex);
            if((currentTotalGas + currentGas) >= currentCost) {
                System.out.println("currentTotalGas + currentGas: " + (currentTotalGas + currentGas) +
                        ", currentCost: " + currentCost);
                currentTotalGas = currentTotalGas + currentGas - currentCost;
                currentStationCount ++;
            } else {
                if(currentStationCount == 0) {
                    startStationIndex ++;
                } else {
                    while (startStationIndex < stationCount  &&
                            (currentStationCount > 0 || (currentTotalGas >= currentCost))) {
                        int startGas = gas[startStationIndex];
                        int startCost = cost[startStationIndex];
                        currentTotalGas = currentTotalGas + startCost - startGas;
                        currentStationCount --;
                        startStationIndex  ++;
                        System.out.println("move startStationIndex: " + startStationIndex +
                                ", currentStationCount: " + currentStationCount);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Program134().canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }
}
