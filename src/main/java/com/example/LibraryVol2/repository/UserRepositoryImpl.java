package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository<PersonDTO> {

    private JdbcOperations jdbcOperations;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void addAUser(PersonDTO personDTO) {
        Object[] params = new Object[] {personDTO.getLogin(), personDTO.getPassword()};
        jdbcOperations.update("INSERT INTO users(login, password)VALUES (?,?);",params);
    }

    @Override
    public boolean checkEqualsLogin(PersonDTO personDTO) {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? ;";
        Object[] params = new Object[] {personDTO.getLogin()};
        Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
        if(count != null){
            return true;
        }
        else return false;
    }

    @Override
    public boolean login(PersonDTO personDTO) {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ? ;";
        Object[] params = new Object[] {personDTO.getLogin(), personDTO.getPassword()};
        Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
        if(count != null){
            return true;
        }
        else return false;
    }
}
