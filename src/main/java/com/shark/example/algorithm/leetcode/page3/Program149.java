package com.shark.example.algorithm.leetcode.page3;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Program149 {

    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        int max = 0;
        Map<Double, Map<Double, Set<int[]>>> slopDividentPointMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int[] point1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] point2 = points[j];
                double slop;
                double dividend;
                if (point1[0] == point2[0]) {
                    slop = Integer.MIN_VALUE;
                    dividend = point2[0];
                } else {
                    slop = ((double) (point1[1] - point2[1]) / (double) (point1[0] - point2[0]));
                    dividend = (double) point1[1] - slop * point1[0];
                }

                Map<Double, Set<int[]>> dividendSlopMap = slopDividentPointMap.getOrDefault(slop, new HashMap<>());
                Set<int[]> pointsSet = dividendSlopMap.getOrDefault(dividend, new HashSet<>());
                pointsSet.add(point1);
                pointsSet.add(point2);
                dividendSlopMap.put(dividend, pointsSet);
                slopDividentPointMap.put(slop, dividendSlopMap);
                if (pointsSet.size() > max) {
                    max = pointsSet.size();
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{7, 3}, {19, 19}, {-16, 3}, {13, 17}, {-18, 1}, {-18, -17}, {13, -3}, {3, 7}, {-11, 12}, {7, 19}, {19, -12}, {20, -18}, {-16, -15}, {-10, -15}, {-16, -18}, {-14, -1}, {18, 10}, {-13, 8}, {7, -5}, {-4, -9}, {-11, 2}, {-9, -9}, {-5, -16}, {10, 14}, {-3, 4}, {1, -20}, {2, 16}, {0, 14}, {-14, 5}, {15, -11}, {3, 11}, {11, -10}, {-1, -7}, {16, 7}, {1, -11}, {-8, -3}, {1, -6}, {19, 7}, {3, 6}, {-1, -2}, {7, -3}, {-6, -8}, {7, 1}, {-15, 12}, {-17, 9}, {19, -9}, {1, 0}, {9, -10}, {6, 20}, {-12, -4}, {-16, -17}, {14, 3}, {0, -1}, {-18, 9}, {-15, 15}, {-3, -15}, {-5, 20}, {15, -14}, {9, -17}, {10, -14}, {-7, -11}, {14, 9}, {1, -1}, {15, 12}, {-5, -1}, {-17, -5}, {15, -2}, {-12, 11}, {19, -18}, {8, 7}, {-5, -3}, {-17, -1}, {-18, 13}, {15, -3}, {4, 18}, {-14, -15}, {15, 8}, {-18, -12}, {-15, 19}, {-9, 16}, {-9, 14}, {-12, -14}, {-2, -20}, {-3, -13}, {10, -7}, {-2, -10}, {9, 10}, {-1, 7}, {-17, -6}, {-15, 20}, {5, -17}, {6, -6}, {-11, -8}};
//        int[][] points = new int[][]{{4, 5}, {4, -1}, {4, 0}};
        System.out.println("result: " + new Program149().maxPoints(points));
    }
}
