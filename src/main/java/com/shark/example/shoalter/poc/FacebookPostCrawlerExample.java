package com.shark.example.shoalter.poc;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FacebookPostCrawlerExample {

    public ProfileDto generateProfile(String url) {
        ProfileDto profileDto = new ProfileDto();
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36").get();
            Element titleElement = document.getElementsByAttributeValue("property", "og:title").get(0);
            String title = titleElement.attr("content");
            profileDto.setName(title);
            Element imageElement = document.getElementsByAttributeValue("property", "og:image").get(0);
            String imageUrl = imageElement.attr("content");
            profileDto.setImage(imageUrl);
            System.out.println(imageUrl);
            Files.write(Paths.get("file/html/fb.html"), document.html().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return profileDto;
    }

    public static void main(String[] args) {
        String url = "https://www.facebook.com/adaymag/";
        FacebookPostCrawlerExample example = new FacebookPostCrawlerExample();
        Gson gson = new Gson();
        ProfileDto profileDto = example.generateProfile(url);
        System.out.println(gson.toJson(profileDto));
    }

}
