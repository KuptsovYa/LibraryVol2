package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.entity.PageDto;

public interface BookService {

    void addBook(BookDto bookDTO);

    String[][] getAllBooks(PageDto pageDto);
}
