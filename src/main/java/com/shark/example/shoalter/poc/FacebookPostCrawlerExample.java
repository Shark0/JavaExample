package com.shark.example.shoalter.poc;

import com.google.gson.Gson;
import com.shark.example.file.txt.ReadTxtFileExample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FacebookPostCrawlerExample {

    public void downloadHtml(String url) {
        Gson gson = new Gson();
        System.setProperty("webdriver.chrome.driver", "file/driver/chrome/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(url);
            Thread.sleep(10000);
            String pageSource = driver.getPageSource();
            Files.write(Paths.get("file/html/fb.html"), pageSource.getBytes());
            Document document = Jsoup.parse(pageSource);
            Element body = document.body();
            Files.write(Paths.get("file/html/fb_body.html"), body.html().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public void parserBody() {
        ReadTxtFileExample readTxtFileExample = new ReadTxtFileExample();
        String html = readTxtFileExample.readFile("file/html/fb.html");
        Document doc = Jsoup.parse(html);
        Element body = doc.body();
//        System.out.println(body.html());
        Element element =
                body.getElementsByAttributeValue("role", "main").get(0).child(3)
                        .child(1).child(0).child(1).child(0);
        System.out.println(element.html());
    }

    public static void main(String[] args) {
        String url = "https://www.facebook.com/JesseTang11/";
        FacebookPostCrawlerExample example = new FacebookPostCrawlerExample();
        example.downloadHtml(url);
//        example.parserBody();
    }

}
