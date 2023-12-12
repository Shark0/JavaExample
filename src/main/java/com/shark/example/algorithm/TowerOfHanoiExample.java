package com.shark.example.algorithm;

public class TowerOfHanoiExample {


    public static void main(String[] argv) {
        int discCount = 4;
        String sourceName = "A";
        String auxiliaryName = "B";
        String destinationName = "C";
        System.out.println("Step:");
        towerOfHanoi(discCount, sourceName, auxiliaryName, destinationName);
    }

    public static void towerOfHanoi(int discCount, String source, String auxiliary, String destination) {
        if (discCount == 1) {
            System.out.println("Move disc 1 from " + source + " to " + destination);
            return;
        }
        //move n-1 from source to auxiliary
        towerOfHanoi(discCount - 1, source, destination, auxiliary);
        System.out.println("Move disc " + discCount + " from " + source + " to " + destination);
        //move n-1 from auxiliary to destination
        towerOfHanoi(discCount - 1, auxiliary, source, destination);
    }
}
