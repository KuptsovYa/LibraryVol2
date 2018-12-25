package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonDto;

public interface UserService {

    boolean addAUser(PersonDto personDTO);

    boolean checkEqualsLogin(String login);

}
