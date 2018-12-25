package com.example.LibraryVol2.controllers.defapi.index;

import com.example.LibraryVol2.configuration.ExecutorsSubmitBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex(){
        return "index1";
    }

}
