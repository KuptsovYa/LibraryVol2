package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDto;

public interface BookRepository<B extends BookDto>  {

    boolean addBook(B b);

}
