package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.PersonalDto;
import com.example.LibraryVol2.repository.PersonalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PersonalServiceImpl implements PersonalService {

    private PersonalRepository<PersonalDto> personalRepository;
    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public PersonalServiceImpl(PersonalRepository<PersonalDto> personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Override
    public PersonalDto getPersonal(String login) {
        PersonalDto person = new PersonalDto();
        try {
            logger.error("Getting personal user data of user " + login);
            List<Map<String, Object>> personal = personalRepository.getPersonalInfo(login);
            logger.error("Personal data of user " + login + " was taken successfully");
            List<String> result = new ArrayList<>();
            if (personal.size() == 0) {
                return null;
            }
            for (Map.Entry<String, Object> entry : personal.get(0).entrySet()) {
                result.add((String) entry.getValue());
            }
            person = new PersonalDto(result.get(0), result.get(1), result.get(2));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return person;
    }

    @Override
    public PersonalDto insertPersonalData(PersonalDto personal, String login) {
        try {
            logger.info("Inserting personal data " + personal.toString() + "of user " + login);
            personal.setLogin(login);
            personalRepository.insertPersonalInfo(personal);
            logger.info("Personal data was added to db");
            return personal;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
