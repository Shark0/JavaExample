package com.shark.example.parser;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class ParserDouble {

    public static void main(String[] argv) {
        try {
            identify("0.6%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void identify(String input) {
        try {
            String refinedInput = input.trim().replace(",", "");
            if (refinedInput.endsWith("%")) {
                refinedInput = refinedInput.replace("%", "").trim();
                int scale = 0;
                if(refinedInput.contains(".")) {
                    scale = refinedInput.substring(refinedInput.trim().indexOf(".")).length() + 1;
                } else {
                    scale = 2;
                }
                System.out.println("scale: " + scale);
                BigDecimal parsedInput = BigDecimal.valueOf(Double.parseDouble(refinedInput)).divide(BigDecimal.valueOf(100), scale, HALF_UP);
                refinedInput = String.valueOf(parsedInput);
            }
            int dotIndex = refinedInput.trim().indexOf(".");
            if (dotIndex > 0 && refinedInput.startsWith("0")) {

                Double value = Double.parseDouble(refinedInput);
                System.out.println(value);
            } else {
                System.out.println("dotIndex:" + dotIndex);
                System.out.println(refinedInput.startsWith("0"));
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
