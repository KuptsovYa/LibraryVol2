package com.example.LibraryVol2.controllers.defapi.index;

import com.example.LibraryVol2.configuration.ExecutorsSubmitBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private ExecutorsSubmitBean executorsSubmitBean;

    @Autowired
    public IndexController(ExecutorsSubmitBean executorsSubmitBean) {
        this.executorsSubmitBean = executorsSubmitBean;
    }

    @GetMapping("/")
    public String getIndex(){
//        executorsSubmitBean.init();
        return "index1";
    }

}
