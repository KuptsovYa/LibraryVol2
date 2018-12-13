package com.example.LibraryVol2.service;

import com.example.LibraryVol2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class BookService  implements BookRepository {

    private DataSource dataSource;

    @Autowired
    public BookService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean addBook(String author, String title, String content){
//        System.out.println(author + "  " +title + "  " + content);
        String sql = "INSERT INTO books(author, title, content) VALUES (?,?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, author, title, content);
        return true;
    }
}
