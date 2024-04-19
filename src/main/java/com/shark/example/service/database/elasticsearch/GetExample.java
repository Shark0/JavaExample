package com.shark.example.service.database.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

public class GetExample {

    public static void main(String[] argv) {
        try(RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("es.qa.sis.ai", 9200, "http")))) {
            getData(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getData(RestHighLevelClient client) {
        try {
//            GetResponse getResponse = client.get(getRequest("797"), RequestOptions.DEFAULT);
            GetIndexRequest request = new GetIndexRequest("797");
            boolean exist = client.indices().exists(request, RequestOptions.DEFAULT);
            System.out.println("exist: " + exist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GetRequest getRequest(String dataSourceId) {
        GetRequest request = new GetRequest(dataSourceId);
        String[] includes = new String[]{"data_frame_id"};
        String[] excludes = new String[]{"values"};
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
//        request.routing(value);
        return request;
    }

}
