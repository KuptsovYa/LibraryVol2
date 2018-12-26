package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonalDto;

public interface PersonalService {

    PersonalDto getPersonal(String login);

    PersonalDto insertPersonalData(PersonalDto personal, String login);
}
