package com.shark.example.earler.operator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ParserOperatorExample {

    public static void main(String argv[]) {
        String json = "[{\"type\":\"column\",\"value\":58715},{\"type\":\"operator\",\"value\":\"/\"},{\"type\":\"column\",\"value\":58716},{\"type\":\"operator\",\"value\":\"/\"},{\"type\":\"operator\",\"value\":\"(\"},{\"type\":\"column\",\"value\":58717},{\"type\":\"operator\",\"value\":\"-\"},{\"type\":\"column\",\"value\":58715},{\"type\":\"operator\",\"value\":\")\"}]";
        Gson gson = new Gson();
        List<OperatorItemDo> operatorItemList = gson.fromJson(json, new TypeToken<List<OperatorItemDo>>(){}.getType());
        System.out.println(gson.toJson(operatorItemList));
        System.out.println("operator: " + generatorOperator(operatorItemList));
        System.out.println("nullif: " + generatorNullIfOperator(operatorItemList));
    }

    private static String generatorOperator(List<OperatorItemDo> operatorItemList) {
        StringBuilder operatorStringBuilder = new StringBuilder();
        for(OperatorItemDo item: operatorItemList) {
            operatorStringBuilder.append(item.getValue());
            operatorStringBuilder.append(" ");
        }
        return operatorStringBuilder.toString();
    }

    private static String generatorNullIfOperator(List<OperatorItemDo> operatorItemList) {
        StringBuilder operatorStringBuilder = new StringBuilder();
        int prefixCount = 0;
        int prefixIndex = -1;
        int suffixCount = 0;
        int suffixIndex = -1;
        boolean hasNullIf = false;
        int index = 0;
        while (index < operatorItemList.size()) {
            OperatorItemDo item = operatorItemList.get(index);
            if("column".equalsIgnoreCase(item.getType())) {
                if(hasNullIf) {
                    operatorStringBuilder.append("NULLIF(").append("#column").append(", 0)");
                    hasNullIf = false;
                } else {
                    operatorStringBuilder.append("#column");
                }
            } else if("operator".equalsIgnoreCase(item.getType())) {
                if("/".equalsIgnoreCase(item.getValue())) {
                    operatorStringBuilder.append(item.getValue());
                    hasNullIf = true;
                } else if("(".equalsIgnoreCase(item.getValue())) {
                    if(hasNullIf) {
                        prefixIndex = index;
                        prefixCount = prefixCount + 1;
                        suffixIndex = index + 1;
                        while (suffixIndex < operatorItemList.size()) {
                            OperatorItemDo parenthesesItem = operatorItemList.get(suffixIndex);
                            if("(".equalsIgnoreCase(parenthesesItem.getValue())) {
                                prefixCount = prefixCount + 1;
                            } else if(")".equalsIgnoreCase(parenthesesItem.getValue())) {
                                suffixCount = suffixCount + 1;
                                System.out.println("suffixCount: " + suffixCount);

                                if(prefixCount == suffixCount) {
                                    List<OperatorItemDo> subList = operatorItemList.subList(prefixIndex, suffixIndex + 1);
                                    System.out.println("subList: " + new Gson().toJson(subList));
                                    operatorStringBuilder.append("NULLIF(")
                                            .append(generatorNullIfOperator(subList)).append(")");
                                    index = suffixIndex + 1;
                                    break;
                                }
                            }
                            suffixIndex ++;
                        }
                        hasNullIf = false;
                    } else {
                        operatorStringBuilder.append(item.getValue());
                    }
                } else {
                    operatorStringBuilder.append(item.getValue());
                }
            } else {
                //numeric
                if(hasNullIf) {
                    operatorStringBuilder.append("NULLIF(").append(item.getValue()).append(", 0)");
                    hasNullIf = false;
                } else {
                    operatorStringBuilder.append(item.getValue());
                }
            }
            operatorStringBuilder.append(" ");
            index = index + 1;
        }
        return operatorStringBuilder.toString();
    }

}
