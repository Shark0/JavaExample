package com.shark.example.algorithm.leetcode.page49;

import java.util.*;

public class Program2402 {

    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(meeting -> meeting[0]));
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            freeRooms.offer(i);
        }

        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1])
                        : Long.compare(a[0], b[0]));

        int[] roomUseCountArray = new int[n];
        int currentResult = 0;
        int currentMaxCount = 0;

        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];

            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                freeRooms.offer((int) busyRooms.poll()[1]);
            }

            int roomNumber;
            long newEndTime;
            if (!freeRooms.isEmpty()) {
                roomNumber = freeRooms.poll();
                newEndTime = end;
            } else {
                long[] minEndTimeMeetingRoom = busyRooms.poll();
                roomNumber = (int) minEndTimeMeetingRoom[1];
                newEndTime = minEndTimeMeetingRoom[0] + (end - start);
            }

            busyRooms.offer(new long[] {newEndTime, roomNumber});
            int roomUseCount = roomUseCountArray[roomNumber] + 1;
            roomUseCountArray[roomNumber] = roomUseCount;

            if (roomUseCount > currentMaxCount) {
                currentMaxCount = roomUseCount;
                currentResult = roomNumber;
            }  else if  (roomUseCount == currentMaxCount) {
                if (roomNumber < currentResult) {
                    currentResult = roomNumber;
                }
            }
            System.out.println("roomNumber: " + roomNumber + ", roomUseCount: " + roomUseCount + ", endTime: " + newEndTime + ", currentResult: " + currentResult + ", currentMaxCount: " + currentMaxCount);
        }

        return currentResult;

    }

    public int mostBookedBackup(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(meeting -> meeting[0]));
        int currentResult = 0;
        int currentMaxCount = 0;
        Map<Integer, Integer> meetingRoomUseCountMap = new HashMap<>();
        Map<Integer, Integer> meetingRoomEndTimeMap = new HashMap<>();
        for (int[] meeting : meetings) {

            int roomNumber = -1;
            int minEndTime = Integer.MAX_VALUE;
            for(int i = 0; i < n; i ++) {
                int endTime = meetingRoomEndTimeMap.getOrDefault(i, 0);
                if(endTime == 0 || endTime < meeting[0]) {
                    minEndTime = endTime;
                    roomNumber = i;
                    break;
                }
                if(endTime < minEndTime) {
                    minEndTime = endTime;
                    roomNumber = i;
                }
            }

            if(minEndTime == 0 || minEndTime < meeting[0]) {
                meetingRoomEndTimeMap.put(roomNumber, meeting[1]);
            } else {
                meetingRoomEndTimeMap.put(roomNumber, minEndTime + (meeting[1] - meeting[0] + 1));
            }

            int roomUseCount = meetingRoomUseCountMap.getOrDefault(roomNumber, 0) + 1;
            meetingRoomUseCountMap.put(roomNumber, roomUseCount);
            System.out.println("meeting: " + Arrays.toString(meeting));
            if (roomUseCount > currentMaxCount) {
                currentMaxCount = roomUseCount;
                currentResult = roomNumber;
            } else if (roomUseCount == currentMaxCount) {
                if (roomNumber < currentResult) {
                    currentResult = roomNumber;
                }
            }
            System.out.println("roomNumber: " + roomNumber + ", roomUseCount: " + roomUseCount + ", endTime: " + meetingRoomEndTimeMap.get(roomNumber) + ", currentResult: " + currentResult + ", currentMaxCount: " + currentMaxCount);
        }

        return currentResult;
    }


    public static void main(String[] args) {
        int result = new Program2402().mostBooked(2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}});
        System.out.println("result = " + result);

//        result = new Program2402().mostBooked(3, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}});
//        System.out.println("result = " + result);
    }
}
