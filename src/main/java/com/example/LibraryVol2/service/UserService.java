package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.UserDto;

public interface UserService {

    UserDto addAUser(UserDto userDTO);

    boolean checkEqualsLogin(String login);

}
