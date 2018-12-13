package com.example.LibraryVol2.logic;

import com.example.LibraryVol2.controllers.restapi.profile.Book;
import com.example.LibraryVol2.repository.BookRepository;
import com.example.LibraryVol2.repository.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {

    private final LinkedBlockingQueue<Book> mainQueue;
    private final BookRepository bookRepository;
    private final WordsRepository wordsRepository;
    private final AtomicBoolean stop;

    @Autowired
    public Consumer(LinkedBlockingQueue<Book> mainQueue, BookRepository bookRepository, WordsRepository wordsRepository, AtomicBoolean stop){
        this.mainQueue = mainQueue;
        this.bookRepository = bookRepository;
        this.wordsRepository = wordsRepository;
        this.stop = stop;
    }

    public void run(){

        while(!stop.get()){
            System.out.println(mainQueue.size());
            try {
                if(mainQueue.peek() == null){
                    stop.set(true);
                }else{
                    Book b = mainQueue.take();
                    Counter c = new Counter(b.getContent(), wordsRepository);
                    c.count();
//                    System.out.println(mainQueue.size());
                    bookRepository.addBook(b.getAuthor(), b.getTitle(), b.getContent());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
