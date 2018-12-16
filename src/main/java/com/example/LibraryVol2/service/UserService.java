package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonDTO;

public interface UserService {

    boolean addAUser(PersonDTO personDTO);

    boolean checkEqualsLogin(String login);

    boolean login(PersonDTO personDTO);

}
