package com.example.LibraryVol2.controllers.restapi.index;

import com.example.LibraryVol2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class IndexRestController {

    private final UserRepository userRepository;

    @Autowired
    public IndexRestController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PutMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Person person  ){
        userRepository.addAUser(person.getLogin(), person.getPassword());
        return ResponseEntity.ok(true);
    }

    @PutMapping("/loginCheck")
    public ResponseEntity<?> loginEqualsCheck(@RequestBody String login){
        System.out.println(login + " has come to server");
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> map = jsonParser.parseMap(login);
        Object s = map.get("login");
        return ResponseEntity.ok(userRepository.checkEqualsLogin((String) s));
    }

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody Person person){
        return ResponseEntity.ok(userRepository.login(person.getLogin(), person.getPassword()));
    }
}
