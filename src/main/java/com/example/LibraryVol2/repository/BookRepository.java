package com.example.LibraryVol2.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {

    boolean addBook(String author, String title, String content);

}
