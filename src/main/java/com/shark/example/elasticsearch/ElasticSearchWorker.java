package com.shark.example.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.flush.FlushResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.elasticsearch.index.IndexSettings.INDEX_REFRESH_INTERVAL_SETTING;
import static org.elasticsearch.index.IndexSettings.INDEX_TRANSLOG_DURABILITY_SETTING;

public class ElasticSearchWorker {

    RestHighLevelClient client;

    public ElasticSearchWorker(String url) {
        String[] address = url.split(":");
        HttpHost httpHost = new HttpHost(
                address[1].replace("//", ""),
                Integer.parseInt(address[2]),
                address[0]);
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost)
                .setRequestConfigCallback(builder -> builder
                        .setConnectTimeout(10000)
                        .setConnectionRequestTimeout(0)
                        .setSocketTimeout(360000))
                .setHttpClientConfigCallback(builder -> builder
                        .setMaxConnTotal(100)
                        .setMaxConnPerRoute(5));
        client = new RestHighLevelClient(restClientBuilder);
    }

    public void createIndex(String index) throws IOException {
        if (isIndexExist(index)) {
            deleteIndex(index);
        }
        // set mapping
        Map<String, Object> value = Map.ofEntries(
                entry("type", "keyword"));
        Map<String, Object> tableId = Map.ofEntries(
                entry("type", "long"),
                entry("index", false));
        Map<String, Object> columnId = Map.ofEntries(
                entry("type", "long"),
                entry("index", false));
        Map<String, Object> properties = Map.ofEntries(
                entry("table_id", tableId),
                entry("column_id", columnId),
                entry("value", value));

        Map<String, Object> mapping = Map.ofEntries(
                entry("properties", properties));

        CreateIndexRequest request = new CreateIndexRequest(index);
        // shard count setting
        request.settings(Settings.builder()
                .put("index.number_of_shards", 6)
                .put("index.number_of_replicas", 0)
        );
        request.mapping(mapping);

        AcknowledgedResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("createIndex: " + response.isAcknowledged());
    }

    public boolean isIndexExist(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    public void deleteIndex(String index) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
        deleteIndexRequest.indices(index);
        try {
            client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsertValues(String index, Long dataFrameId, Long dataColumnId, List<String> values) throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.NONE);
        for (String value : values) {
            bulkRequest.add(createIndexRequest(index, dataFrameId, dataColumnId, value));
        }
        BulkRequestAction bulkTask = new BulkRequestAction(client);
        bulkTask.start(bulkRequest);
    }

    public void setIndexRefreshTime(String index, String refreshTime) throws Exception {

        UpdateSettingsRequest request = new UpdateSettingsRequest(index);
        Settings.Builder settingsBuilder = Settings.builder()
                .put(INDEX_REFRESH_INTERVAL_SETTING.getKey(), refreshTime)
                .put(INDEX_TRANSLOG_DURABILITY_SETTING.getKey(), "async");
        request.settings(settingsBuilder);
        AcknowledgedResponse response = client.indices().putSettings(request, RequestOptions.DEFAULT);
        System.out.println("setIndexRefreshTime: " + response.isAcknowledged());
    }

    private IndexRequest createIndexRequest(String index, Long tableId, Long columnId, String value) {
        String id = tableId + "_" + columnId + "_" + value;
        Map<String, Object> json = Map.ofEntries(
                entry("table_id", tableId),
                entry("column_id", columnId),
                entry("value", value));
        return new IndexRequest(index).id(id).source(json);
    }

    public void refreshIndex(String index) throws Exception {
        RefreshRequest refreshRequest = new RefreshRequest(index);
        RefreshResponse refreshResponse = client.indices().refresh(refreshRequest, RequestOptions.DEFAULT);
        System.out.println("refreshResponse state" + refreshResponse.getStatus().getStatus());
        FlushRequest flushRequest = new FlushRequest(index);
        FlushResponse flushResponse = client.indices().flush(flushRequest, RequestOptions.DEFAULT);
        System.out.println("flushResponse state" + flushResponse.getStatus().getStatus());
    }


    @RequiredArgsConstructor
    static class BulkRequestAction implements ActionListener<BulkResponse> {

        private final RestHighLevelClient client;

        public void start(BulkRequest request) {
            long bulkStartTime = System.currentTimeMillis();
            try {
//                client.bulkAsync(request, RequestOptions.DEFAULT, this);
                BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
                System.out.println("bulkResponse: " + bulkResponse.status().getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
            long bulkEndTime = System.currentTimeMillis();
            System.out.println("bulk time: " + (bulkEndTime - bulkStartTime) + " millis");
        }

        @Override
        public void onResponse(BulkResponse bulkResponse) {
            if (bulkResponse.hasFailures()) {
                for (BulkItemResponse itemResponse : bulkResponse.getItems()) {
                    if (itemResponse.isFailed()) {
                        System.out.println(itemResponse.getFailureMessage());
                    } else {
                        System.out.println("Successfully indexed id: " + itemResponse.getId() + " to " + itemResponse.getIndex());
                    }
                }
            } else {
                String index = bulkResponse.getItems()[0].getIndex();
                System.out.println("Successfully index " + index + " with " + bulkResponse.getItems().length + " values");
            }
        }

        @Override
        public void onFailure(Exception e) {
            e.printStackTrace();
        }
    }
}
