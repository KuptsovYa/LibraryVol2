package com.example.LibraryVol2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("WordsRepository")
public class WordsRepositoryImpl implements WordsRepository{

    private JdbcOperations jdbcOperations;

    @Autowired
    public WordsRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public String getWord(String wordFrmTxt){
        try {
            String sql = "SELECT word FROM improperwords WHERE word = ?;";
            List<String> result = jdbcOperations.queryForList(sql,
                    new Object[] { wordFrmTxt }, String.class);
            if(!result.isEmpty()){
                return result.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Correct word";
    }

    public List<String> getAllWords(){
        List<String> result = new ArrayList<String>();
        try{
            String sql = "SELECT word FROM improperwords;";
            result = jdbcOperations.queryForList(sql, String.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
