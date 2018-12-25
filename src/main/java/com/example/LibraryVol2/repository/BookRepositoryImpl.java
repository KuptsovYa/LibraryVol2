package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("BookRepository")
public class BookRepositoryImpl implements BookRepository<BookDto>{

    private JdbcOperations jdbcOperations;

    @Autowired
    public BookRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean addBook(BookDto bookDTO) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO books(author, title, content, users_idusers) VALUES (?,?,?)";
            Object[] params = new Object[] {bookDTO.getAuthor(), bookDTO.getTitle(), bookDTO.getContent()};
            jdbcOperations.update(sql, params);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }
}
