package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDTO;

public interface BookRepository<B extends BookDTO>  {

    boolean addBook(B b);

}
