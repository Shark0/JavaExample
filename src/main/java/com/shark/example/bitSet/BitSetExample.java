package com.shark.example.bitSet;

import java.util.BitSet;

public class BitSetExample {
    public static void main(String[] argv) {
        BitSet bitSet = new BitSet(4);
        for(int i = 0; i < 4; i ++) {
            bitSet.set(i);
        }
        for(int i = 0; i < 10; i ++) {
            System.out.println("next: " + bitSet.nextSetBit(i));
            System.out.println("preview: " + bitSet.previousSetBit(i));
        }
        System.out.println("bitSet length: " + bitSet.length());

    }
}
