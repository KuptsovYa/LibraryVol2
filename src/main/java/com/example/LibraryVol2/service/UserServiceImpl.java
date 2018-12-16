package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonDTO;
import com.example.LibraryVol2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository =  userRepository;
    }

    public boolean addAUser(PersonDTO personDTO){
        try{
            userRepository.addAUser(personDTO);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEqualsLogin(String login){
       try {
           PersonDTO personDTO = new PersonDTO();
           personDTO.setLogin(login);
           userRepository.checkEqualsLogin(personDTO);
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    public boolean login(PersonDTO personDTO){
        try{
            userRepository.login(personDTO);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
