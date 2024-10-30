package com.shark.example.algorithm.leetcode.page19;

import java.util.ArrayList;
import java.util.List;

public class Program901 {

    public List<Integer> priceList = new ArrayList<>();
    private int previewComparePrice = -1;
    private int previewComparePriceIndex = 0;
    private int previewResult = 0;

    public int next(int price) {
        priceList.add(price);
        int result = 0;
        if(price > previewComparePrice) {
            result = previewResult + 1;
            if(previewComparePriceIndex != 0) {
                while (previewComparePriceIndex > 0) {
                    int value = priceList.get(previewComparePriceIndex - 1);
                    if(value > price) {
                        break;
                    }
                    result ++;
                    previewComparePriceIndex--;
                }
            }
        } else if(price == previewComparePrice) {
            result = previewResult + 1;
        } else {
            result = 1;
            previewComparePriceIndex = priceList.size() - 1;
        }
        previewComparePrice = price;
        previewResult = result;
        return result;
    }


    public static void main(String[] args) {
        Program901 p = new Program901();
        int[] prices = new int[]{100, 80, 60, 70, 60};
        for(int price : prices) {
            System.out.println("result = " + p.next(price));
        }
    }
}
