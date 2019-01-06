package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.dto.ConfigDto;
import com.example.LibraryVol2.entity.BooksEntity;

import java.util.List;
import java.util.Map;

public interface BookRepository<B extends BookDto>  {

    boolean addBook(B b);

    Long getIdByName(String name);

    List<Map<String, Object>> getAllBooks(ConfigDto configDto);

    List<Map<String, Object>> getPersonalBooks(ConfigDto configDto);

    String getContentByTitle(BooksEntity booksEntity);
}
