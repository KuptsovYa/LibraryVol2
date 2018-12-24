package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.PersonDto;
import com.example.LibraryVol2.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository<PersonDto> {

    private JdbcOperations jdbcOperations;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void addAUser(PersonDto personDTO) {
        Object[] params = new Object[] {personDTO.getLogin(), personDTO.getPassword()};
        jdbcOperations.update("INSERT INTO users(login, password)VALUES (?,?);",params);
    }

    @Override
    public boolean checkEqualsLogin(PersonDto personDTO) {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? ;";
        Object[] params = new Object[] {personDTO.getLogin()};
        Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
        if(count != null){
            return true;
        }
        else return false;
    }

    @Override
    public boolean login(PersonDto personDTO) {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ? ;";
        Object[] params = new Object[] {personDTO.getLogin(), personDTO.getPassword()};
        Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
        if(count != null){
            return true;
        }
        else return false;
    }

    @Override
    public UsersEntity findByLogin(String login) {
        String sql = "SELECT login, password FROM users WHERE login = ?";
        Object[] params = new Object[] {login};
        PersonDto result = (PersonDto) jdbcOperations.queryForObject(sql, params, new BeanPropertyRowMapper(PersonDto.class));
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(result.getLogin());
        usersEntity.setPassword(result.getPassword());
        return usersEntity;
    }
}
