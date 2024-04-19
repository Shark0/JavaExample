package com.shark.example.data.list;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSortExample {

    public static void main(String[] argv) {
        List<ValueDo> valueList = new ArrayList<>();
        valueList.add(new ValueDo(0.09571517642099561));
        valueList.add(new ValueDo(2));
        valueList.add(new ValueDo(3));
        valueList.add(new ValueDo(4));

        Collections.sort(valueList, (v1, v2) -> {
            if(v1.value > v2.value) {
               return -1;
            } else if(v1.value == v2.value) {
                return 0;
            } else {
                return 1;
            }
        });
        System.out.println("valueList: " + new Gson().toJson(valueList));
    }

    private static class ValueDo {
        private double value;

        public ValueDo(double value) {
            this.value = value;
        }

    }
}
