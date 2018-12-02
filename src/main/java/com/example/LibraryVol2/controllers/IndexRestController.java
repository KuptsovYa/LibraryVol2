package com.example.LibraryVol2.controllers;

import com.example.LibraryVol2.repository.UserRepository;
import com.example.LibraryVol2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

@RestController
public class IndexRestController {

    private int counter = 0;

    @Autowired
    UserService userService;

    public IndexRestController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Person person  ){
        userService.addAUser(person.getLogin(), person.getPassword());
        return ResponseEntity.ok(true);
    }

    @PutMapping("/loginCheck")
    public ResponseEntity<?> loginEqualsCheck(@RequestBody String login){
        System.out.println(login + " has come to server");
        return ResponseEntity.ok(userService.checkEqualsLogin(login));
    }

}
