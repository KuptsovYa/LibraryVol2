package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.UserDto;
import com.example.LibraryVol2.entity.UsersEntity;
import com.example.LibraryVol2.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addAUser(UserDto userDTO) {
        try {
            logger.info("Adding new user " + userDTO);
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setLogin(userDTO.getLogin());
            usersEntity.setPassword(userDTO.getPassword());
            userRepository.addAUser(usersEntity);
            logger.info("New user " + userDTO + " added database");
            return new UserDto();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public boolean checkEqualsLogin(String login) {
        try {
            logger.info("Checking for already registred user " + login);
            UsersEntity user = new UsersEntity();
            return userRepository.checkEqualsLogin(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }


}
