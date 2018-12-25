package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonDto;
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

    public boolean addAUser(PersonDto personDTO) {
        try {
            System.out.println(personDTO);
            userRepository.addAUser(personDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEqualsLogin(String login) {
        PersonDto personDTO = new PersonDto();
        personDTO.setLogin(login);
        return userRepository.checkEqualsLogin(personDTO);
    }


}
