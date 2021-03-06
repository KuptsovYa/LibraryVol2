package com.example.LibraryVol2.service;

import com.example.LibraryVol2.entity.UsersEntity;
import com.example.LibraryVol2.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LogManager.getLogger(this);
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("User with username " + s + " trying to authorise");
        try {
            UsersEntity usersEntity = userRepository.findByLogin(s);
            if (usersEntity == null){
                throw new UsernameNotFoundException("User with login: " + s + " not found");
            }
            return new UserDetailsImpl(usersEntity);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

}
