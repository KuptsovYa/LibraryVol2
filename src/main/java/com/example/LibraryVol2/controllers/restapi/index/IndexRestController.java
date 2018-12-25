package com.example.LibraryVol2.controllers.restapi.index;

import com.example.LibraryVol2.dto.PersonDto;
import com.example.LibraryVol2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexRestController {

    private final UserService userService;

    @Autowired
    public IndexRestController(UserService userService){
        this.userService = userService;
    }

    @PutMapping("/registration")
    public PersonDto registration(@RequestBody PersonDto personDTO){
        PersonDto person = new PersonDto(personDTO.getLogin(), personDTO.getPassword());
        if (userService.addAUser(person)){
            return person;
        }
        else {
          return person;
        }
    }

    @GetMapping("/loginCheck/{login}")
    public String loginEqualsCheck(@PathVariable String login){
        String loginNew = login;
        if (userService.checkEqualsLogin(loginNew)){
            return loginNew;
        }
        else {
            return "fail";
        }
    }

}
