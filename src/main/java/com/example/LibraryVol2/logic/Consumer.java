package com.example.LibraryVol2.logic;

import com.example.LibraryVol2.controllers.restapi.profile.Book;
import com.example.LibraryVol2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {

    private final LinkedBlockingQueue<Book> mainQueue;
    private final BookRepository bookRepository;
    private final AtomicBoolean stop;

    @Autowired
    public Consumer(LinkedBlockingQueue<Book> mainQueue, BookRepository bookRepository, AtomicBoolean stop){
        this.mainQueue = mainQueue;
        this.bookRepository = bookRepository;
        this.stop = stop;
    }

    public void run(){

        while(!stop.get()){
            System.out.println(mainQueue.size());
            try {
                if(mainQueue.peek() == null){
                    stop.set(true);
                }else{
                    //Какая-то джоба которая считает небалгоприятные слова
                    Book b = mainQueue.take();
                    System.out.println(mainQueue.size());
                    bookRepository.addBook(b.getAuthor(), b.getTitle(), b.getContent());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
