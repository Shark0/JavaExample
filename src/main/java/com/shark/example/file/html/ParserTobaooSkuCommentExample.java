package com.shark.example.file.html;

import com.google.gson.Gson;
import com.shark.example.file.txt.ReadTxtFileExample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ParserTobaooSkuCommentExample {

    public List<Comment> parser(String fileName) {
        List<Comment> comments = new ArrayList<>();
        String text = new ReadTxtFileExample().readFile(fileName);
        text = text.replace("< ", "<");
        text = text.replace("< / ", "</");
        text = text.replace(" >", ">");
//        System.out.println(text);
        Document doc = Jsoup.parse(text);
        Element commentListElement = doc.getElementsByAttributeValue("style",
                "height: auto; overflow-y: hidden; padding-bottom: 0px; padding-right: 0px; margin-right: 0px;").get(0);

        Elements commentElements = commentListElement.getElementsByAttributeValueContaining("class", "Comment--");

        for (Element commentElement : commentElements) {
            System.out.println("commentElement: " + commentElement.text());
        }

        return comments;
    }


    public static void main(String[] args) {
        List<Comment> comments = new ParserTobaooSkuCommentExample().parser("file/taboo_sku.html");
        System.out.println(new Gson().toJson(comments));
    }

    public static class Comment {
        private String content;
        private String likeCount;
    }

}
