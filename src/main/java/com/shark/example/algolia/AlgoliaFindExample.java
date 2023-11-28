package com.shark.example.algolia;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.google.gson.Gson;

import java.util.List;

public class AlgoliaFindExample {
    public static void main(String[] argv) {
        SearchClient client = DefaultSearchClient.create(AlgoliaConfig.APPLICATION_ID, AlgoliaConfig.API_KEY);
        SearchIndex<BookDto> index = client.initIndex("book", BookDto.class);
        SearchResult<BookDto> searchResult = index.search(new Query("NAME_9").setAttributesToRetrieve(List.of("name")).setHitsPerPage(20));
        List<BookDto> bookDtoList = searchResult.getHits();
        System.out.println(new Gson().toJson(bookDtoList));
    }
}
