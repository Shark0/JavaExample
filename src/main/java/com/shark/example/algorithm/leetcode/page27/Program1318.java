package com.shark.example.algorithm.leetcode.page27;

public class Program1318 {

    public int minFlips(int a, int b, int c) {
        int result = 0;
        while (a != 0 || b != 0 || c != 0) {
            System.out.println("a = " + a + ", b = " + b + ", c = " + c);
            int aBit = a % 2;
            a = a >> 1;
            int bBit = b % 2;
            b = b >> 1;
            int cBit = c % 2;
            c = c >> 1;
            System.out.println("aBit = " + aBit + ", bBit = " + bBit + ", cBit = " + cBit);
            if(cBit == 0) {
                if(aBit == 1 && bBit == 1) {
                    //change a and b
                    result = result + 2;
                }
                if(aBit == 1 && bBit == 0) {
                    //change a
                    result++;
                }
                if(aBit == 0 && bBit == 1) {
                    //change b
                    result++;
                }
            } else {
                if(aBit == 0 && bBit == 0) {
                    //change a or b
                    result ++;
                }
            }
            System.out.println("result = " + result);
        }
        return result;
    }

    public static void main(String[] args) {
        int a = 2;
        int b = 6;
        int c = 5;
        System.out.println("result: " + new Program1318().minFlips(a, b, c));
    }
}
