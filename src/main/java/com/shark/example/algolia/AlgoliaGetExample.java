package com.shark.example.algolia;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.google.gson.Gson;

import java.util.List;

public class AlgoliaGetExample {

    public static void main(String argv[]) {
        SearchClient client = DefaultSearchClient.create(AlgoliaConfig.APPLICATION_ID, AlgoliaConfig.API_KEY);
        SearchIndex<BookDto> index = client.initIndex("book", BookDto.class);
        List<BookDto> bookDtoList = index.getObjects(List.of("1", "2", "3"));
        System.out.println(new Gson().toJson(bookDtoList));
    }
}
