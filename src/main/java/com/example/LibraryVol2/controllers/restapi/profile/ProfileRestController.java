package com.example.LibraryVol2.controllers.restapi.profile;

import com.example.LibraryVol2.configuration.IAuthenticationFacade;
import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.dto.PersonalDto;
import com.example.LibraryVol2.dto.ConfigDto;
import com.example.LibraryVol2.service.BookService;
import com.example.LibraryVol2.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileRestController {

    private final BookService bookService;
    private final IAuthenticationFacade authenticationFacade;
    private final PersonalService personalService;

    @Autowired
    public ProfileRestController(BookService bookService, IAuthenticationFacade authenticationFacade,PersonalService personalService) {
        this.bookService = bookService;
        this.authenticationFacade = authenticationFacade;
        this.personalService = personalService;
    }

    @GetMapping("/profile/getPersonal")
    public PersonalDto getPersonalInfo(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return personalService.getPersonal(authentication.getName());
    }

    @PostMapping("/profile/addPersonal")
    public PersonalDto addPersonal(@RequestBody PersonalDto personalDto){
        Authentication authentication = authenticationFacade.getAuthentication();
        return personalService.insertPersonalData(personalDto, authentication.getName());
    }

    @PostMapping("/profile/addBook")
    public BookDto addBook(@RequestBody BookDto bookDTO){
        Authentication authentication = authenticationFacade.getAuthentication();
        bookDTO.setUserName(authentication.getName());
        bookService.addBook(bookDTO);
        return bookDTO;
    }

    @PostMapping("/profile/getAllBooks")
    public String[][] getAllBooks(@RequestBody ConfigDto configDto){
        Authentication authentication = authenticationFacade.getAuthentication();
        configDto.setUserName(authentication.getName());
        return bookService.getAllBooks(configDto);
    }


}
