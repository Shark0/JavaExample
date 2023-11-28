package com.shark.example.algolia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookWorker {
    public List<BookDto> generateInsertBookList() {
        List<BookDto> bookDtoList = new ArrayList<>();
        for(int i = 0; i < 100; i ++) {
            BookDto bookDto = new BookDto();
            bookDto.setObjectId(String.valueOf(i));
            bookDto.setName("NAME_" + i);
            bookDto.setDescription("DESCRIPTION_" + i);
            bookDto.setPrice(BigDecimal.valueOf(100.99 + i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    public List<BookDto> generateUpdateBookList() {
        List<BookDto> bookDtoList = new ArrayList<>();
        for(int i = 0; i < 100; i ++) {
            BookDto bookDto = new BookDto();
            bookDto.setObjectId(String.valueOf(i));
            bookDto.setName("NAME_" + i);
            bookDto.setDescription("DESCRIPTION_" + i);
            bookDto.setPrice(BigDecimal.valueOf(200.99 + i));
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }
}
