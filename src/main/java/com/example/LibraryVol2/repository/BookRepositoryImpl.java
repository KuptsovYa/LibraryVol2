package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.dto.ConfigDto;
import com.example.LibraryVol2.entity.BooksEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

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
        String sql = "INSERT INTO books(author, title, content, users_idusers, improperWordsCount) VALUES (?,?,?,?,?)";
        Long id = getIdByName(bookDTO.getUserName());
        Object[] params = new Object[]{bookDTO.getAuthor(), bookDTO.getTitle(), bookDTO.getContent(), id, bookDTO.getUnDesirable()};
        jdbcOperations.update(sql, params);
        return true;
    }

    @Override
    public Long getIdByName(String name) {
        String sql = "SELECT idusers FROM users WHERE login = ?";
        Long result = jdbcOperations.queryForObject(sql, new Object[]{name}, Long.class);
        return result;
    }

    @Override
    public List<Map<String, Object>> getPersonalBooks(ConfigDto configDto) {
        String sql = "SELECT idbooks, author, title FROM books WHERE users_idusers = ? LIMIT ?, 10;";
        List<Map<String, Object>> result = jdbcOperations.queryForList(sql, new Object[]{configDto.getUserId(), configDto.getPage() * 10});
        return result;
    }

    @Override
    public List<Map<String, Object>> getAllBooks(ConfigDto configDto) {
        String sql = "SELECT idbooks, author, title FROM books WHERE 1 = 1 LIMIT ?, 10;";
        List<Map<String, Object>> result = jdbcOperations.queryForList(sql, new Object[]{configDto.getPage() * 10});
        return result;
    }

    @Override
    public String getContentByTitle(BooksEntity booksEntity) {
        String sql = "SELECT content FROM books WHERE idbooks = ?";
        String result = jdbcOperations.queryForObject(sql, new Object[]{booksEntity.getIdbooks()}, String.class);
        return result;
    }
}
