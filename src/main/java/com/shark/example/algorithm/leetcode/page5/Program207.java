package com.shark.example.algorithm.leetcode.page5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import com.google.gson.Gson;

public class Program207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Set<Integer>> preRequestMap = new HashMap<>();
        for(int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            Set<Integer> requests = preRequestMap.getOrDefault(course, new HashSet<>());
            requests.add(prerequisite[1]);
            preRequestMap.put(prerequisite[0], requests);
        }
        Set<Integer> totalCourses = new HashSet<>();
        while(!preRequestMap.isEmpty()) {
            Set<Integer> checkSet = new HashSet<>();
            Set<Integer> visitedSet = new HashSet<>();
            int currentCourse = preRequestMap.keySet().iterator().next();
            boolean result = checkCourse(currentCourse, preRequestMap, checkSet, visitedSet);
            if(!result) {
                return false;
            }
            preRequestMap.remove(currentCourse);
            for(Integer visitedCourse : visitedSet) {
                preRequestMap.remove(visitedCourse);
            }
            totalCourses.addAll(visitedSet);
        }
        return numCourses >= totalCourses.size();
    }

    private boolean checkCourse(
            int currentCourse, HashMap<Integer, Set<Integer>> preRequestMap,
            Set<Integer> checkSet, Set<Integer> visitedSet) {
        if(checkSet.contains(currentCourse)) {
            return false;
        }
        if(visitedSet.contains(currentCourse)) {
            return true;
        }
        visitedSet.add(currentCourse);
        Set<Integer> currentCoursePreRequestSet = preRequestMap.get(currentCourse);
        if(currentCoursePreRequestSet == null || currentCoursePreRequestSet.isEmpty()) {
            return true;
        }
        checkSet.add(currentCourse);
        while(!currentCoursePreRequestSet.isEmpty()) {
            int nextCourse = currentCoursePreRequestSet.iterator().next();
            boolean result = checkCourse(nextCourse, preRequestMap, checkSet, visitedSet);
            if(!result) {
                checkSet.remove(currentCourse);
                return false;
            }
            currentCoursePreRequestSet.removeAll(visitedSet);
        }
        checkSet.remove(currentCourse);
        return true;
    }

    public static void main(String[] args) {
        int numCourses = 3;
        int[][] prerequisites = new int[][] {
                {0, 1},
                {0, 2},
                {1, 2}
        };
        System.out.println(new Program207().canFinish(numCourses, prerequisites));
    }
}
