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
    public UserRepositoryImpl(JdbcOperations jdbcOperations, PasswordEncoder passwordEncoder) {
        this.jdbcOperations = jdbcOperations;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addAUser(UsersEntity user) {

//            String hashedPass = passwordEncoder.encode(user.getPassword());
//            String sql = "BEGIN;" +
//                    "INSERT INTO users(login, password) VALUES (?, ?);" +
//                    "SELECT LAST_INSERT_ID() INTO @users_idusers;" +
//                    "INSERT INTO roles(role, users_idusers) VALUES (?, @users_idusers);" +
//                    "COMMIT;";
//            Object[] params = new Object[]{user.getLogin(), hashedPass, user.getRolesByIdusers().getRole()};
//            for (Object o: params){
//                System.out.println(o);
//            }
//            jdbcOperations.update(sql, params);

        String hashedpass = passwordEncoder.encode(user.getPassword());
        String queryForUser = "INSERT INTO users(login, password) VALUES (?, ?)";
        Object[] params = new Object[]{user.getLogin(), hashedpass};
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

