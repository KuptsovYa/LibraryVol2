package com.example.LibraryVol2.configuration;

import com.example.LibraryVol2.contextListener.PostProxy;
import com.example.LibraryVol2.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImproperWordsBean {

    private List<String> improperWords;

    private WordsService wordsService;

    @Autowired
    public ImproperWordsBean(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @PostProxy
    public void initialization(){
        this.improperWords = wordsService.getAllWords();
    }

    public ArrayList<String> getImproperWords() {
        return new ArrayList<>(improperWords);
    }

}
