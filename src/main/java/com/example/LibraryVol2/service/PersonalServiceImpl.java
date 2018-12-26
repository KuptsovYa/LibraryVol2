package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonalDto;
import com.example.LibraryVol2.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PersonalServiceImpl implements PersonalService{

    PersonalRepository<PersonalDto> personalRepository;

    @Autowired
    public PersonalServiceImpl(PersonalRepository<PersonalDto> personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Override
    public PersonalDto getPersonal(String login) {
        List<Map<String, Object>> personal = personalRepository.getPersonalInfo(login);
        List<String> result = new ArrayList<>();
        for ( Map.Entry<String, Object> entry : personal.get(0).entrySet()) {
            result.add((String)entry.getValue());
        }
        PersonalDto person = new PersonalDto(result.get(0), result.get(1), result.get(2));
        return person;
    }

    @Override
    public PersonalDto insertPersonalData(PersonalDto personal, String login) {
        personal.setLogin(login);
        return personalRepository.insertPersonalInfo(personal);
    }
}
