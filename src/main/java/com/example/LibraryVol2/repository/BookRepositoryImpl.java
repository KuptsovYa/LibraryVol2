package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository<BookDto> {

    private JdbcOperations jdbcOperations;

    @Autowired
    public BookRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean addBook(BookDto bookDTO) {
        String sql = "INSERT INTO books(author, title, content, users_idusers) VALUES (?,?,?)";
        Object[] params = new Object[]{bookDTO.getAuthor(), bookDTO.getTitle(), bookDTO.getContent()};
        jdbcOperations.update(sql, params);
        return true;
    }

    @Override
    public List<Map<String, Object>> getAllBooks() {
        String sql = "SELECT idbooks, author, title FROM books WHERE 1 = 1 LIMIT 0, 10;";
        List<Map<String, Object>> result = jdbcOperations.queryForList(sql);
        return result;
    }
}
