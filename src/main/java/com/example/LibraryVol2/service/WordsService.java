package com.example.LibraryVol2.service;

import com.example.LibraryVol2.repository.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class WordsService implements WordsRepository {

    private DataSource dataSource;

    @Autowired
    public WordsService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getWord(String wordFrmTxt){
        try {
            String sql = "SELECT word FROM improperwords WHERE word = ?;";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<String> result = jdbcTemplate.queryForList(sql, // Пришлось здесь взять листу, если брать просто стринг, причем слово будет корректным,
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
