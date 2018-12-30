package com.example.LibraryVol2.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface WordsRepository {

    List<String> getAllWords();
}
