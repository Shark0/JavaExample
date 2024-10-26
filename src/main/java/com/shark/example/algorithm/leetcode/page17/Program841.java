package com.shark.example.algorithm.leetcode.page17;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        Set<Integer> unVisitedRoomkeySet = new HashSet<>();
        unVisitedRoomkeySet.add(0);
        Set<Integer> visitedRoomSet = new HashSet<>();
        while (!unVisitedRoomkeySet.isEmpty()) {
            Integer key = unVisitedRoomkeySet.iterator().next();
            List<Integer> roomKeyList = rooms.get(key);
            visitedRoomSet.add(key);
            for(Integer roomKey : roomKeyList) {
                if(!visitedRoomSet.contains(roomKey)) {
                    unVisitedRoomkeySet.add(roomKey);
                }
            }
            unVisitedRoomkeySet.remove(key);
        }

        return visitedRoomSet.size() == rooms.size();
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = List.of(List.of(1), List.of(2), List.of(3), List.of());
        System.out.println(new Program841().canVisitAllRooms(rooms));
    }
}
