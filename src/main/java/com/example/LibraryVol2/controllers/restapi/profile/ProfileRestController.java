package com.example.LibraryVol2.controllers.restapi.profile;

import com.example.LibraryVol2.controllers.restapi.index.Person;
import com.example.LibraryVol2.logic.Starter;
import com.example.LibraryVol2.repository.BookRepository;
import com.example.LibraryVol2.repository.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.LinkedBlockingQueue;

@RestController
public class ProfileRestController {

    private final LinkedBlockingQueue<Book> mainQueue;
    private final Starter starter;
    private final BookRepository bookRepository;
    private final WordsRepository wordsRepository;

    @Autowired
    public ProfileRestController(BookRepository bookRepository, WordsRepository wordsRepository){
        this.wordsRepository = wordsRepository;
        this.bookRepository = bookRepository;
        this.mainQueue = new LinkedBlockingQueue<Book>();
        this.starter = new Starter(5, mainQueue);
    }

    @PostMapping("/profile/addBook")
    public boolean addBook(@RequestBody Book book){
        mainQueue.add(book);
        starter.start(bookRepository, wordsRepository);
        return true;
    }

//    @GetMapping("/showAll")
//    public ResponseEntity<?> showAll(){
//        bookRepository
//    }
}
