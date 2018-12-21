package com.example.LibraryVol2.configuration;

import com.example.LibraryVol2.contextListener.PostProxy;
import com.example.LibraryVol2.dto.BookDTO;
import com.example.LibraryVol2.logic.Consumer;
import com.example.LibraryVol2.logic.Worker;
import com.example.LibraryVol2.repository.BookRepository;
import com.example.LibraryVol2.repository.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class ExecutorsSubmitBean {

    private ExecutorService executorService;
    private ImproperWordsBean improperWordsBean;
    private BookRepository bookRepository;
    private LinkedBlockingQueue queue;

    @Autowired
    public ExecutorsSubmitBean(ExecutorService executorService,
                               ImproperWordsBean improperWordsBean,
                               BookRepository bookRepository,
                               LinkedBlockingQueue queue) {
        this.executorService = executorService;
        this.improperWordsBean = improperWordsBean;
        this.bookRepository = bookRepository;
        this.queue = queue;
    }

    private int nThreads = 5;

    @PostProxy
    public void init(){
        for (int i =0; i < nThreads; i++){
            Consumer consumer = new Consumer(queue, new Worker(improperWordsBean, bookRepository));
            executorService.submit(consumer);
        }
    }

    @PreDestroy
    public void cleanup(){
        executorService.shutdown();
    }

}
