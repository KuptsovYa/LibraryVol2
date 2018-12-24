package com.example.LibraryVol2.controllers.restapi.profile;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.service.BookService;
import com.example.LibraryVol2.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileRestController {


    private final BookService bookService;
    private final WordsService wordsService;

    @Autowired
    public ProfileRestController(BookService bookService, WordsService wordsService) {
        this.bookService = bookService;
        this.wordsService = wordsService;
    }

    @PostMapping("/profile/addBook")
    public boolean addBook(@RequestBody BookDto bookDTO){
        bookService.addBook(bookDTO);
        return true;
    }

//    @GetMapping("/showAll")
//    public ResponseEntity<?> showAll(){
//        bookRepository
//    }
}
