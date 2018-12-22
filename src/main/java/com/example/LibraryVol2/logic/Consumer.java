package com.example.LibraryVol2.logic;

import com.example.LibraryVol2.dto.BookDTO;
import com.example.LibraryVol2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class Consumer implements Runnable {

    private final LinkedBlockingQueue<BookDTO> mainQueue;
    private final Worker worker;

    @Autowired
    public Consumer(LinkedBlockingQueue<BookDTO> mainQueue, Worker worker){
        this.mainQueue = mainQueue;
        this.worker = worker;
    }

    public void run(){
        while(true){
            try {
                    BookDTO b = mainQueue.take();
                    worker.addToDB(b);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
