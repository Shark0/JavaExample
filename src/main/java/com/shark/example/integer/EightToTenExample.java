package com.shark.example.integer;

public class EightToTenExample {
    public static void main(String[] argv) {
        String octalString = "1537032260";
        Integer value = Integer.valueOf(octalString, 8);
        System.out.println("value: " + value);

        octalString = Integer.toOctalString(value);
        System.out.println("octalString: " + octalString);
    }
}
