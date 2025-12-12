package com.shark.example.algorithm.leetcode.page2;

public class Program65 {

    public boolean isNumber(String s) {
        //I: init, N: number, E: exponent, S: sign, D: dot
        char previewState = 'I';
        char[] chars = s.toCharArray();
        boolean hasNumber = false;
        boolean hasExponent = false;
        boolean hasDot = false;
        boolean isExponentBeforeNumber = true;
        for(int i = 0; i < s.length(); i++) {
            char current = chars[i];
            System.out.println("current: " + current);
            switch(current) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    hasNumber = true;
                    isExponentBeforeNumber = true;
                    previewState = 'N';
                    break;
                case '+':
                case '-':
                    if(previewState == 'I' || previewState == 'E') {
                        previewState ='S';
                    } else {
                        return false;
                    }
                    break;
                case '.':
                    if(previewState == 'I' || previewState == 'N' || previewState == 'S') {
                        if(hasExponent) {
                            return false;
                        }
                        if(hasDot) {
                            return false;
                        }
                        hasDot = true;
                        previewState = 'D';
                    } else {
                        return false;
                    }
                    break;
                case 'E':
                case 'e':
                    if(previewState == 'N' || previewState == 'D') {
                        if(hasExponent || !hasNumber) {
                            return false;
                        }
                        hasExponent = true;
                        isExponentBeforeNumber = false;
                        previewState = 'E';
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return hasNumber && isExponentBeforeNumber;
    }

    public static void main(String[] args) {
        Program65 program65 = new Program65();
        System.out.printf("result: " + program65.isNumber("2"));
    }
}
