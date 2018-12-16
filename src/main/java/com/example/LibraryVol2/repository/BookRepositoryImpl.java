package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("BookRepository")
public class BookRepositoryImpl implements BookRepository<BookDTO>{

    private JdbcOperations jdbcOperations;

    @Autowired
    public BookRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean addBook(BookDTO bookDTO) {
        String sql = "INSERT INTO books(author, title, content) VALUES (?,?,?)";
        Object[] params = new Object[] {bookDTO.getAuthor(), bookDTO.getTitle(), bookDTO.getContent()};
        jdbcOperations.update(sql, params);
        return true;
    }
}
