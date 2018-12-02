package com.example.LibraryVol2.service;

import com.example.LibraryVol2.service.UserService;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DataSource dataSource;

    public void addAUser(String login, String password){
        System.out.println(login + " " + password );
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO users(login, password)VALUES (?,?)",login,password);
    }

    public boolean checkEqualsLogin(String login){
        System.out.println(login);
        String sql = "SELECT COUNT(*) FROM users WHERE login ='"+login +"'";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int count = jdbcTemplate.queryForObject(sql, new Object[] {login}, Integer.class);
        if(count < 1){
            return true;
        }
        else return false;
    }
}
