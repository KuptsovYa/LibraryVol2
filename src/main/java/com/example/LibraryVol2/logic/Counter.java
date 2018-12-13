package com.example.LibraryVol2.logic;

import com.example.LibraryVol2.repository.WordsRepository;

import java.awt.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Counter {

    private HashMap<String, Integer> unDesirable = new HashMap<String, Integer>();
    private String text;
    private WordsRepository wordsRepository;

    public Counter(String text, WordsRepository wordsRepository){
        this.text = text;
        this.wordsRepository = wordsRepository;
    }

    public void count(){
        String text = getText();
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
                if (key.equals(wordsRepository.getWord(key))){
                    sumCount += value;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(sumCount + " Summary count of equals words");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
