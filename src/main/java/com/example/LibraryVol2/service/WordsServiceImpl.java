package com.example.LibraryVol2.service;

import com.example.LibraryVol2.repository.WordsRepository;
import com.example.LibraryVol2.service.WordsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service("WordService")
public class WordsServiceImpl implements WordsService {

    private WordsRepository wordsRepository;
    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public WordsServiceImpl(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    public List<String> getAllWords(){
        logger.info("Getting improper words form database");
        List<String> result = new ArrayList<>();
        try {
            result = wordsRepository.getAllWords();
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }
}
