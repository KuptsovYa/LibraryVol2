package com.example.LibraryVol2.service;

import com.example.LibraryVol2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public interface UserService {

    void addAUser(String login, String password);
    boolean checkEqualsLogin(String login);
}
