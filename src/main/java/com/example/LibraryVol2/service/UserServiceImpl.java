package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.UserDto;
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

    public boolean addAUser(UserDto userDTO) {
        try {
            System.out.println(userDTO);
            userRepository.addAUser(userDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEqualsLogin(String login) {
        UserDto userDTO = new UserDto();
        userDTO.setLogin(login);
        return userRepository.checkEqualsLogin(userDTO);
    }


}
