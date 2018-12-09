package com.example.LibraryVol2.controllers.defapi.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @PostMapping("/profile")
    public String getProfile(@RequestParam(value="login", required=false) String login, ModelMap model){
        model.addAttribute("login", login);
        return "profile";
    }

}