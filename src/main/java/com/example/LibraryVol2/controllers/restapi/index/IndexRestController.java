package com.example.LibraryVol2.controllers.restapi.index;

import com.example.LibraryVol2.dto.PersonDTO;
import com.example.LibraryVol2.repository.UserRepository;
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
    public ResponseEntity<?> registration(@RequestBody PersonDTO personDTO){
        if (userService.addAUser(personDTO))return ResponseEntity.ok(true);
        else return ResponseEntity.ok(false);
    }

    @GetMapping("/loginCheck/{login}")
    public ResponseEntity<?> loginEqualsCheck(@PathVariable String login){
        if (userService.checkEqualsLogin(login))return ResponseEntity.ok(true);
        else return ResponseEntity.ok(false);
    }

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody PersonDTO personDTO){
        if (userService.login(personDTO))return ResponseEntity.ok(true);
        else return ResponseEntity.ok(false);
    }
}
