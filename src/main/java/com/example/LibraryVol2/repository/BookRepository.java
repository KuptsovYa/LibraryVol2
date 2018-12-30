package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDto;

import java.util.List;
import java.util.Map;

public interface BookRepository<B extends BookDto>  {

    boolean addBook(B b);

    List<Map<String, Object>> getAllBooks();
}
