package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.UserDto;
import com.example.LibraryVol2.entity.UsersEntity;
import com.example.LibraryVol2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addAUser(UserDto userDTO) {
        try {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setLogin(userDTO.getLogin());
            usersEntity.setPassword(userDTO.getPassword());
            userRepository.addAUser(usersEntity);
            return new UserDto();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkEqualsLogin(String login) {
        UsersEntity user = new UsersEntity();
        return userRepository.checkEqualsLogin(user);
    }


}
