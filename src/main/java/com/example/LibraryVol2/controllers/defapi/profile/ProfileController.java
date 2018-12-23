package com.example.LibraryVol2.controllers.defapi.profile;

import com.example.LibraryVol2.dto.PersonDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

//    @PostMapping("/profile")
//    @ResponseBody
//    public String getProfile(){
//        return "profile";
//    }
//
    @GetMapping("/profile")
    @ResponseBody
    public String getProfile(){
        return "profile";
    }

}