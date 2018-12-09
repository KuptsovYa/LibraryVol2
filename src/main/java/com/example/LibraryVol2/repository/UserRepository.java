package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository{

    void addAUser(String login, String password);
    boolean checkEqualsLogin(String login);
    boolean login(String login, String password);
}
