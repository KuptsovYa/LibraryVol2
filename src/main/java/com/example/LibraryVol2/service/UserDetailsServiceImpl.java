package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonDto;
import com.example.LibraryVol2.entity.UsersEntity;
import com.example.LibraryVol2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UsersEntity usersEntity = userRepository.findByLogin(s);
        if (usersEntity == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(usersEntity);
    }

}
