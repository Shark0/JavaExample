package com.shark.example.data.string;

public class DecimalToOctalExample {

    public static void main(String[] argv) {
        String value = "88-1640842897-11-9";
        StringBuilder newValueStringBuilder = new StringBuilder();
        int index = 0;
        for(String subValue: value.split("-")) {
            if(index != 0) {
                newValueStringBuilder.append("-");
            }
            newValueStringBuilder.append(Integer.toOctalString(Integer.valueOf(subValue)));
            index = index + 1;
        }
        String newValue = newValueStringBuilder.toString();
        System.out.println(newValue);
    }
}
