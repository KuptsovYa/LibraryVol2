package com.example.LibraryVol2.controllers.restapi.profile;

import com.example.LibraryVol2.logic.Starter;
import com.example.LibraryVol2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;

@RestController
public class ProfileRestController {

    private final LinkedBlockingQueue<Book> mainQueue;
    private final Starter starter;
    private final BookRepository bookRepository;

    @Autowired
    public ProfileRestController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.mainQueue = new LinkedBlockingQueue<Book>();
        this.starter = new Starter(5, mainQueue);
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(Book book){
        Book testBook = new Book("Author", "Title", "Content");
        mainQueue.add(testBook);
        starter.start(bookRepository);
        return ResponseEntity.ok("Book added to Lib");
    }

//    @GetMapping("/showAll")
//    public ResponseEntity<?> showAll(){
//        bookRepository
//    }
}
