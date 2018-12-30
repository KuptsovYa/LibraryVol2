package com.example.LibraryVol2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WordsRepositoryImpl implements WordsRepository{

    private JdbcOperations jdbcOperations;

    @Autowired
    public WordsRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public List<String> getAllWords(){
        String sql = "SELECT word FROM improperwords";
        List<String> result = jdbcOperations.queryForList(sql, String.class);
        return result;
    }
}
