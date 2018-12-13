package com.example.LibraryVol2.service;

import com.example.LibraryVol2.entity.User;
import com.example.LibraryVol2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Optional;

@Service
public class UserService implements UserRepository {

    private DataSource dataSource;

    @Autowired
    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addAUser(String login, String password){
        System.out.println(login + " " + password );
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO users(login, password)VALUES (?,?);",login,password);
    }

    public boolean checkEqualsLogin(String login){
        System.out.println(login);
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? ;";
        System.out.println(sql);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer count = jdbcTemplate.queryForObject(sql, new Object[] {login}, Integer.class);
        if(count != null){
            return true;
        }
        else return false;
    }

    public boolean login(String login, String password){
        String sql = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ? ;";
        System.out.println(sql);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer count = jdbcTemplate.queryForObject(sql, new Object[] {login, password}, Integer.class);
        if(count != null){
            return true;
        }
        else return false;
    }

}
