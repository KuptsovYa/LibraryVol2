package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.PersonDto;
import com.example.LibraryVol2.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository<PersonDto> {

    private JdbcOperations jdbcOperations;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations, PasswordEncoder passwordEncoder) {
        this.jdbcOperations = jdbcOperations;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addAUser(PersonDto personDTO) {
        try {
            String hashedPass = passwordEncoder.encode(personDTO.getPassword());
            Object[] params = new Object[] {personDTO.getLogin(), hashedPass};
            jdbcOperations.update("INSERT INTO users(login, password)VALUES (?,?);",params);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkEqualsLogin(PersonDto personDTO) {
        boolean flag = false;
        try {
            String sql = "SELECT COUNT(*) FROM users WHERE login = ? ;";
            Object[] params = new Object[] {personDTO.getLogin()};
            Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
            if(count != null){
                flag = true;
            }else flag = false;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return flag;
        }

    }


    @Override
    public UsersEntity findByLogin(String login) {
        UsersEntity usersEntity = new UsersEntity();
        try {
            String sql = "SELECT login, password FROM users WHERE login = ?";
            Object[] params = new Object[] {login};
            PersonDto result = (PersonDto) jdbcOperations.queryForObject(sql, params, new BeanPropertyRowMapper(PersonDto.class));
            usersEntity.setLogin(result.getLogin());
            usersEntity.setPassword(result.getPassword());
            return usersEntity;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return usersEntity;
        }
    }
}
