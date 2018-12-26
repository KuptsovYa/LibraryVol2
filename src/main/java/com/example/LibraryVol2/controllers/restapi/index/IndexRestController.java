package com.example.LibraryVol2.controllers.restapi.index;

import com.example.LibraryVol2.dto.UserDto;
import com.example.LibraryVol2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexRestController {

    private final UserService userService;

    @Autowired
    public IndexRestController(UserService userService){
        this.userService = userService;
    }

    @PutMapping("/registration")
    public UserDto registration(@RequestBody UserDto userDTO){
        UserDto person = new UserDto(userDTO.getLogin(), userDTO.getPassword());
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
