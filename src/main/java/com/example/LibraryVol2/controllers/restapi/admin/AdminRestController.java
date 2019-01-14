package com.example.LibraryVol2.controllers.restapi.admin;

import com.example.LibraryVol2.dto.ConfigDto;
import com.example.LibraryVol2.dto.PersonalDto;
import com.example.LibraryVol2.service.BookService;
import com.example.LibraryVol2.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {

    private final BookService bookService;

    @Autowired
    public AdminRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/admin/getAllBooksImproper")
    public String[][] getAllBooks(@RequestBody ConfigDto configDto){
        return bookService.getAllBooksImproper(configDto);
    }
}
