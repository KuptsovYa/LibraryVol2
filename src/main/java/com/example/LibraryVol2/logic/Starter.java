package com.example.LibraryVol2.logic;

import com.example.LibraryVol2.controllers.restapi.profile.Book;
import com.example.LibraryVol2.repository.BookRepository;
import com.example.LibraryVol2.repository.WordsRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Starter {

    private ExecutorService consumers;
    private LinkedBlockingQueue<Book> mainQueue;
    private final AtomicBoolean stop;

    public Starter(int nThreads, LinkedBlockingQueue<Book> mainQueue){
        this.consumers = Executors.newFixedThreadPool(nThreads);
        this.mainQueue = mainQueue;
        this.stop = new AtomicBoolean(false);
    }

    public void start(BookRepository bookRepository, WordsRepository wordsRepository){
        System.out.println(mainQueue.size());
        if(mainQueue.size() == 0 && consumers.isShutdown()){
            stop.set(true);
        }
        consumers.submit(new Consumer(mainQueue, bookRepository, wordsRepository, stop ));
    }

}
