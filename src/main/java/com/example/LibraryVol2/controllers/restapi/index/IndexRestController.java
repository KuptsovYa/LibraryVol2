package com.example.LibraryVol2.controllers.restapi.index;

import com.example.LibraryVol2.dto.UserDto;
import com.example.LibraryVol2.exceptions.FailAddingUserException;
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

    @ResponseStatus
    @PutMapping("/registration")
    public UserDto registration(@RequestBody UserDto userDTO) throws FailAddingUserException{
        if (userService.addAUser(userDTO) == null){
            throw new FailAddingUserException();
        }
        return userDTO;
    }

    @GetMapping("/loginCheck/{login}")
    public String loginEqualsCheck(@PathVariable String login){
        if (userService.checkEqualsLogin(login)){
            return login;
        }
        else {
            return "fail";
        }
    }

}
