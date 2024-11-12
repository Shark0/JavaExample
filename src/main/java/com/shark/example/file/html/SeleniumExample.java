package com.shark.example.file.html;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SeleniumExample {

    public void start(String url) {
        System.setProperty("webdriver.chrome.driver", "file/driver/chrome/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(url);
            Thread.sleep(10000);
            String pageSource = driver.getPageSource();
            Files.write(Paths.get("file/html/selenium.html"), pageSource.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        SeleniumExample seleniumExample = new SeleniumExample();
        String url = "https://www.instagram.com/bluebottle/";
        seleniumExample.start(url);
    }
}
