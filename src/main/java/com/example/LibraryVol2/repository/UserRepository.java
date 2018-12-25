package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.PersonDto;
import com.example.LibraryVol2.entity.UsersEntity;

public interface UserRepository<P extends PersonDto>{

    void addAUser(P p);

    boolean checkEqualsLogin(P p);

    UsersEntity findByLogin(String login);
}
