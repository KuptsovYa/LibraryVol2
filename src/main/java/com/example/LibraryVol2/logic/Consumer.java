package com.example.LibraryVol2.logic;

import com.example.LibraryVol2.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class Consumer implements Runnable {

    private final LinkedBlockingQueue<BookDto> mainQueue;
    private final Worker worker;

    @Autowired
    public Consumer(LinkedBlockingQueue<BookDto> mainQueue, Worker worker){
        this.mainQueue = mainQueue;
        this.worker = worker;
    }

    public void run(){
        while(true){
            try {
                    BookDto b = mainQueue.take();
                    worker.addToDB(b);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
