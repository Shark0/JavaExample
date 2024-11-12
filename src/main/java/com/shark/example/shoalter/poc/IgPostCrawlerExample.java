package com.shark.example.shoalter.poc;

import com.google.gson.Gson;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IgPostCrawlerExample {

    public void start(String url) {
        Gson gson = new Gson();
        System.setProperty("webdriver.chrome.driver", "file/driver/chrome/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(url);
            Thread.sleep(10000);
            String pageSource = driver.getPageSource();
            Files.write(Paths.get("file/html/ig.html"), pageSource.getBytes());
            Document document = Jsoup.parse(pageSource);
            //profile
            ProfileDto profileDto = new ProfileDto();
            Element profileTitleElement = document.getElementsByAttributeValue("property", "og:title").get(0);
            profileDto.setName(profileTitleElement.attr("content"));
            Element profileDescriptionElement = document.getElementsByAttributeValue("property", "og:description").get(0);
            profileDto.setDescription(profileDescriptionElement.attr("content"));
            Element profileImageElement = document.getElementsByAttributeValue("property", "og:image").get(0);
            profileDto.setImage(profileImageElement.attr("content"));
            System.out.println("profile: " + gson.toJson(profileDto));
            //post list
            List<PostDto> postList = new ArrayList<>();
            Element body = document.body();
            Elements elements = body.select("a[href]");;
            for (Element element : elements) {
                String href = element.attr("href");
                if(href.contains("/p/")) {
                    PostDto post = new PostDto();
                    post.setLink(href);
                    Element imageElement = element.getElementsByTag("img").get(0);
                    String imageDescription = imageElement.attr("alt");
                    post.setContent(imageDescription);
                    String imageUrl = imageElement.attr("src");
                    post.setImageUrl(imageUrl);
                    postList.add(post);
                }
            }
            System.out.println("posts: " + gson.toJson(postList));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }


    public static void main(String[] args) {
        IgPostCrawlerExample example = new IgPostCrawlerExample();
        String url = "https://www.instagram.com/bluebottle/";
        example.start(url);
    }

}
