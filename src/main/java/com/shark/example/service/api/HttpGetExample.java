package com.shark.example.service.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpGetExample {

    public static void main(String[] argv) throws IOException {
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client = builder.build();
        String url = "http://localhost:9100/GameLog/InitData?gameNo=130_14001171366_256055417_1";
        HttpUriRequest httpGet = new HttpGet(url);
        httpGet.setHeader("Cookie", "connect.sid=s%3AdvWH1sd3L3DAsFHkfZKKjAe7HcuZ35tZ.Pn0dJ%2Faa68H8Pn6LuVld88r8EGYmrHWqZmt7snM28Fo");
        CloseableHttpResponse response = client.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String responseBody = EntityUtils.toString(entity, "UTF-8");
                System.out.println(responseBody);
            }
        }
    }
}
