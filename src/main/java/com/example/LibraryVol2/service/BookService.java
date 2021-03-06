package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.dto.ConfigDto;

import java.util.List;
import java.util.Map;

public interface BookService {

    void addBook(BookDto bookDTO);

    String[][] getAllBooks(ConfigDto configDto);

    String[][] createArrOfBooks(List<Map<String, Object>> list, int cols, int cnt);

    String getBookContent(BookDto bookDTO);

    String[][] getAllBooksImproper(ConfigDto configDto);

    BookDto deleteBook(BookDto bookDto);
}
