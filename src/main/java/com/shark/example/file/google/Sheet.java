package com.shark.example.file.google;

import com.google.gson.Gson;
import com.shark.example.data.string.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sheet {
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        String url = "https://docs.google.com/spreadsheets/d/1IQ-SQMj3fl6AhydiWegKEVDlvrXbJk-9d7t4qYcbHms/edit?usp=sharing";
        try {
            Document document = Jsoup.connect(url).get();
            Elements rowElements = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
            int index = 0;
            for (Element rowElement : rowElements) {
                String classValue = (index == 0)? "s0": "s1";
                System.out.println("classValue: " + classValue);
                List<String> row = new ArrayList<>();
                Elements columnElements = rowElement.getElementsByClass(classValue);
                for (Element columnElement : columnElements) {
                    String column = columnElement.text();
                    row.add(column);
                }
                boolean hasColumnValue = false;
                for(String columnValue: row) {
                    if(!StringUtil.isEmpty(columnValue)) {
                        hasColumnValue = true;
                    }
                }
                if(hasColumnValue) {
                    list.add(row);
                }
                index ++;
                String html = rowElement.html();
                System.out.println(html);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new Gson().toJson(list));
    }
}
