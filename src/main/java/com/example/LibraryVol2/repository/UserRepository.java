package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.PersonDTO;
import org.springframework.stereotype.Repository;

public interface UserRepository<P extends PersonDTO>{

    void addAUser(P p);

    boolean checkEqualsLogin(P p);

    boolean login(P p);
}
