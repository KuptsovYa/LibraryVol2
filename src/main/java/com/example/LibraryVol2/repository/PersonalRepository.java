package com.example.LibraryVol2.repository;

import com.example.LibraryVol2.dto.PersonalDto;

import java.util.List;
import java.util.Map;

public interface PersonalRepository<P extends PersonalDto>{

    List<Map<String, Object>> getPersonalInfo(String login);

    P insertPersonalInfo(P personal);
}
