package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.PersonalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PersonalRepositoryImpl implements PersonalRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public PersonalRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Map<String, Object>> getPersonalInfo(String login) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            String sql1 = "SELECT firstName, lastName, middleName FROM personal p " +
                    "INNER JOIN users u ON p.users_idusers = u.idusers where u.login = ?";
            String sql2 = "SELECT firstName, lastName, middleName FROM personal" +
                    " WHERE users_idusers = (SELECT idusers FROM users WHERE login = ?)";
            result = jdbcOperations.queryForList(sql1, new Object[]{login});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public PersonalDto insertPersonalInfo(PersonalDto personal) {
        String sql = "INSERT INTO personal(firstName, lastName, middleName, users_idusers)" +
                "VALUES(?, ?, ?, (SELECT idusers FROM users WHERE login = ?))";
        Object[] params = new Object[]{personal.getFirstName(), personal.getLastName(),
                personal.getMiddleName(), personal.getLogin()};
        jdbcOperations.update(sql, params);
        return personal;
    }

}
