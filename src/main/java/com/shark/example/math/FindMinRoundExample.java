package com.shark.example.math;

import java.math.BigDecimal;
import java.util.*;

public class FindMinRoundExample {

    public static void main(String[] argv) {
        List<Double> valueList = new ArrayList<>();
        valueList.add(0.33);
        valueList.add(0.33);
        valueList.add(0.1);
        valueList.add(0.00000001);
        int roundValue = findRoundValue(valueList);
        System.out.println("roundValue: " + roundValue);

        System.out.println("roundValue: " + new BigDecimal(0.001).toPlainString());
    }

    public static int findRoundValue(List<Double> valueList) {
        DoubleSummaryStatistics summaryStatistics = valueList.stream().mapToDouble(x -> x).summaryStatistics();
        Double minValue = summaryStatistics.getMin();
        String minValueString = new BigDecimal(minValue).toPlainString();
        String[] minValueArray = minValueString.split("\\.");
        if(minValueArray.length <= 1) {
            return 0;
        } else {
            int index = 0;
            while (index < minValueArray[1].length())  {
                char checkChar = minValueArray[1].charAt(index);
                String checkString = String.valueOf(checkChar);
                int charIntValue = Integer.valueOf(checkString);
                if(charIntValue > 0) {
                    return index + 2;
                }
                index = index + 1;
            }
        }
        return 2;
    }
}
