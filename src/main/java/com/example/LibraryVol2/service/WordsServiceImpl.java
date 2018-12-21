package com.example.LibraryVol2.service;

import com.example.LibraryVol2.repository.WordsRepository;
import com.example.LibraryVol2.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service("WordService")
public class WordsServiceImpl implements WordsService {

    private WordsRepository wordsRepository;

    public WordsServiceImpl(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    public String getWord(String wordFrmTxt){
        try {
            return wordsRepository.getWord(wordFrmTxt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Correct word";
    }

    public List<String> getAllWords(){
        List<String> result = new ArrayList<>();
        try {
            result = wordsRepository.getAllWords();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
