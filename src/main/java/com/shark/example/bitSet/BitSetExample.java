package com.shark.example.bitSet;

import java.util.BitSet;

public class BitSetExample {
    public static void main(String[] argv) {
        BitSet bitSet = new BitSet(1);
        bitSet.set(12);
        bitSet.set(128);
        System.out.println(bitSet.previousSetBit(0));
        System.out.println("bitSet: " + bitSet.toString());
        System.out.println("bitSet length: " + bitSet.length());
        System.out.println("bitSet size: " + bitSet.size());

    }
}
