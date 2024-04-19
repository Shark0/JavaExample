package com.shark.example.service.database.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlukInsertExample {

    public static final int BLUK_SIZE = 1000;
    public static final int REFRESH_SIZE = 1000000;

    public static void main(String argv[]) {
        String elasticSearchUrl = "http://es.dev.sis.ai:9200";
        ElasticSearchWorker elasticSearchWorker = new ElasticSearchWorker(elasticSearchUrl);
        try {
            String index = "shark";
            elasticSearchWorker.createIndex(index);
            elasticSearchWorker.setIndexRefreshTime(index, "30S");
            List<String> valueList = new ArrayList<>();
            int valueCount = 0;
            for(long i = 0; i < Integer.MAX_VALUE; i ++) {
                long tableId = i / 10000;
                long columnId = i / 100000;
                valueList.add(String.valueOf(i % 10000000));
                if(valueList.size() >= BLUK_SIZE) {
                    elasticSearchWorker.bulkInsertValues(index, tableId, columnId, valueList);
                    valueList = new ArrayList<>();
                    valueCount = valueCount + BLUK_SIZE;
                    System.out.println("valueCount: " + valueCount);
//                    if(valueCount % REFRESH_SIZE == 0) {
//                        elasticSearchWorker.refreshIndex(index);
//                    }
                }
            }
            long refreshStartTime = System.currentTimeMillis();
            elasticSearchWorker.refreshIndex(index);
            long refreshEndTime = System.currentTimeMillis();
            System.out.println("refresh time: " + (refreshEndTime - refreshStartTime));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
