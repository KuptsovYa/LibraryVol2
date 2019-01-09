package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.entity.RolesEntity;
import com.example.LibraryVol2.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository<UsersEntity> {

    private JdbcOperations jdbcOperations;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public void addAUser(UsersEntity user) {
        String queryForUser = "INSERT INTO users(login, password) VALUES (?, ?)";
        Object[] params = new Object[]{user.getLogin(), user.getPassword()};
        jdbcOperations.update(queryForUser, params);
        String lastId = "SELECT LAST_INSERT_ID()";
        Long id = jdbcOperations.queryForObject(lastId, Long.class);
        String queryForRoles = "INSERT INTO roles(role, users_idusers) VALUES (?, ?);";
        jdbcOperations.update(queryForRoles, user.getRolesByIdusers().getRole(), id);
    }

    @Override
    public boolean checkEqualsLogin(UsersEntity user) {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ?";
        Object[] params = new Object[]{user.getLogin()};
        Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public UsersEntity findByLogin(String login) {
        String sql = "SELECT idusers, login, password FROM users WHERE login = ?";
        Object[] params = new Object[]{login};
        UsersEntity usersEntity = (UsersEntity) jdbcOperations.queryForObject(sql, params, new BeanPropertyRowMapper(UsersEntity.class));
        String sql1 = "SELECT role FROM roles WHERE users_idusers = ?";
        String role = jdbcOperations.queryForObject(sql1, new Object[]{usersEntity.getIdusers()}, String.class);
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRole(role);
        usersEntity.setRolesByIdusers(rolesEntity);
        return usersEntity;
    }
}

