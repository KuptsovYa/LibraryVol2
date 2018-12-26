package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.UserDto;
import com.example.LibraryVol2.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository<UserDto> {

    private JdbcOperations jdbcOperations;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations, PasswordEncoder passwordEncoder) {
        this.jdbcOperations = jdbcOperations;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addAUser(UserDto userDTO) {
        try {
            String hashedPass = passwordEncoder.encode(userDTO.getPassword());
            Object[] params = new Object[] {userDTO.getLogin(), hashedPass};
            jdbcOperations.update("INSERT INTO users(login, password)VALUES (?,?);",params);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkEqualsLogin(UserDto userDTO) {
        boolean flag = false;
        try {
            String sql = "SELECT COUNT(*) FROM users WHERE login = ? ;";
            Object[] params = new Object[] {userDTO.getLogin()};
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
            UserDto result = (UserDto) jdbcOperations.queryForObject(sql, params, new BeanPropertyRowMapper(UserDto.class));
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
