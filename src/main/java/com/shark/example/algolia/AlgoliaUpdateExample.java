package com.shark.example.algolia;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;

public class AlgoliaUpdateExample {

    public static void main(String argv[]) {
        SearchClient client = DefaultSearchClient.create(AlgoliaConfig.APPLICATION_ID, AlgoliaConfig.API_KEY);
        SearchIndex<BookDto> index = client.initIndex("book", BookDto.class);
        BookWorker bookWorker = new BookWorker();
        index.partialUpdateObjects(bookWorker.generateUpdateBookList());
    }
}
