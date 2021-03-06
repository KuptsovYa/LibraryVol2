package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.RoleDto;
import com.example.LibraryVol2.dto.UserDto;
import com.example.LibraryVol2.entity.RolesEntity;
import com.example.LibraryVol2.entity.UsersEntity;
import com.example.LibraryVol2.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Logger logger = LogManager.getLogger(this);
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addAUser(UserDto userDTO) {
        try {
            logger.info("Adding new user " + userDTO);
            if (userDTO.getPassword().equals("admin") && userDTO.getLogin().equals("admin")) {
                userDTO.setRoles(new RoleDto("ADMIN"));
            } else {
                userDTO.setRoles(new RoleDto("USER"));
            }
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setLogin(userDTO.getLogin());
            usersEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            usersEntity.setRolesByIdusers(new RolesEntity());
            usersEntity.getRolesByIdusers().setRole(userDTO.getRoles().getAuthority());
            userRepository.addAUser(usersEntity);
            logger.info("New user " + userDTO + " added database");
            return userDTO;
        } catch (Exception e) {
            logger.error("Adding new user failed exception: " + e);
            return null;
        }
    }

    public boolean checkEqualsLogin(String login) {
        try {
            logger.info("Checking for already registred user " + login);
            UsersEntity user = new UsersEntity();
            return userRepository.checkEqualsLogin(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }

    }
}
