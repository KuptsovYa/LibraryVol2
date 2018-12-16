package com.example.LibraryVol2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            List<String> result = jdbcOperations.queryForList(sql, // Пришлось здесь взять листу, если брать просто стринг, причем слово будет корректным,
                    // то он кидает мне вот это
                    new Object[] { wordFrmTxt }, String.class);  // org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
            // потому что jdbc.queryForObject должен вернуть только одну кверю, а не 0 или > 1

            if(!result.isEmpty()){
                return result.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Correct word";
    }

}
