package com.example.LibraryVol2.logic;

import com.example.LibraryVol2.configuration.ImproperWordsBean;
import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Worker {

    private ImproperWordsBean improperWordsBean;
    private BookRepository bookRepository;

    @Autowired
    public Worker(ImproperWordsBean improperWordsBean, BookRepository bookRepository) {
        this.improperWordsBean = improperWordsBean;
        this.bookRepository = bookRepository;
    }

    public void addToDB(BookDto book){
        book.setUnDesirable(countUnDesirableWords(book.getContent()));
        bookRepository.addBook(book);
    }

    public int countUnDesirableWords(String text){
        text = text.replaceAll("[^A-Za-zА-Яа-я0-9\\s]", "");

        String[] textArr = text.split("\\s");
        HashMap<String, Integer> occurrences = new HashMap<>();

        for ( String word : textArr ) {
            Integer oldCount = occurrences.get(word);
            if ( oldCount == null ) {
                oldCount = 0;
            }
            occurrences.put(word, oldCount + 1);
        }

        int sumCount = 0;
        try {
            for ( Map.Entry<String, Integer> entry : occurrences.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (improperWordsBean.getImproperWords().contains(key)){
                    sumCount += value;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(sumCount + " Summary count of equals words");
        return sumCount;
    }
}
