package com.example.LibraryVol2.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository {

    String getWord(String wordFrmTxt);

}
