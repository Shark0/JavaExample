package com.shark.example.shoalter.poc;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IgPostCrawlerExample {

    public PostDto crawlPost(String url) {
        PostDto postDto = new PostDto();
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36").get();
            Element titleElement = document.getElementsByAttributeValue("property", "og:title").get(0);
            postDto.setContent(titleElement.attr("content"));

            Element imageElement = document.getElementsByAttributeValue("property", "og:image").get(0);
            postDto.setImageUrl(imageElement.attr("content"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return postDto;
    }

    public List<PostDto> crawlPostLinkList() {
        List<PostDto> posts = new ArrayList<>();
        File file = new File("file/html/ig.html");
        try {
            Document document = Jsoup.parse(file, "UTF-8");
            Element body = document.body();
            Elements elements = body.select("a[href]");;
            for (Element element : elements) {
                String href = element.attr("href");
                if(href.contains("/p/")) {
                    PostDto post = new PostDto();
                    post.setLink(href);
                    posts.add(post);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    public ProfileDto crawlProfile(String url) {
        ProfileDto profile = new ProfileDto();
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36").get();
            Element titleElement = document.getElementsByAttributeValue("property", "og:title").get(0);
            profile.setName(titleElement.attr("content"));
            Element descriptionElement = document.getElementsByAttributeValue("property", "og:description").get(0);
            profile.setDescription(descriptionElement.attr("content"));
            Element imageElement = document.getElementsByAttributeValue("property", "og:image").get(0);
            profile.setImage(imageElement.attr("content"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return profile;
    }

    public static void main(String[] args) {

        IgPostCrawlerExample example = new IgPostCrawlerExample();
        Gson gson = new Gson();
        ProfileDto profileDto = example.crawlProfile("https://www.instagram.com/bluebottle/");
        System.out.println("profile: " + gson.toJson(profileDto));

        List<PostDto> postLinkList = example.crawlPostLinkList();
        System.out.println("example2Results: " + new Gson().toJson(postLinkList));

        for(PostDto postLink : postLinkList) {
            PostDto post = example.crawlPost(postLink.getLink());
            System.out.println("post: " + new Gson().toJson(post));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
