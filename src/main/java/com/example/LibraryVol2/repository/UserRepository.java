package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.UserDto;
import com.example.LibraryVol2.entity.UsersEntity;

public interface UserRepository<P extends UsersEntity>{

    void addAUser(P p);

    boolean checkEqualsLogin(P p);

    UsersEntity findByLogin(String login);
}
