package com.shark.example.algorithm.leetcode.page2;

import java.util.ArrayList;
import java.util.List;

public class Program60 {

    public String getPermutation(int n, int k) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(String.valueOf(i));
        }
        StringBuilder builder = new StringBuilder();
        int groupSize = 1;
        for(int i = 1; i < n; i ++) {
            groupSize = groupSize * i;
        }
        k = k -1;
        while(list.size() > 1) {
            int index = k / groupSize;
            builder.append(list.remove(index));
            k = k % groupSize;
            groupSize = groupSize / list.size();
        }
        builder.append(list.get(0));
        return builder.toString();
    }

    public static void main(String[] argv) {
        String result = new Program60().getPermutation(3, 2);
        System.out.println(result);
    }


}
