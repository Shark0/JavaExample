package com.shark.example.file.html;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlUtilExample {

    public void start(String url) {
        try (final WebClient webClient = new WebClient()) {
            // 配置 WebClient
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            HtmlPage page = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(5000); // 等待 JavaScript 加載
            String pageAsXml = page.asXml();
            Files.write(Paths.get("file/html/example.html"), pageAsXml.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String url = "https://www.instagram.com/bluebottle/";
        HtmlUtilExample example = new HtmlUtilExample();
        example.start(url);

    }
}
