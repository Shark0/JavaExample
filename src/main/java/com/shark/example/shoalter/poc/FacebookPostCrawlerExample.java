package com.shark.example.shoalter.poc;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class FacebookPostCrawlerExample {

    public List<PostDto> example1(String url) {

        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36").get();
            System.out.println(document.html());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "https://www.instagram.com/bluebottle/";
        FacebookPostCrawlerExample example = new FacebookPostCrawlerExample();
        List<PostDto> postDtoList = example.example1(url);
        System.out.println(new Gson().toJson(postDtoList));
    }

}
