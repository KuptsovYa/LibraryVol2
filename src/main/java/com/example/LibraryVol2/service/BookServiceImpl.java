package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service("BookService")
public class BookServiceImpl implements BookService {

    private LinkedBlockingQueue<BookDTO> queue;

    @Autowired
    public BookServiceImpl(LinkedBlockingQueue<BookDTO> queue) {
        this.queue = queue;
    }

    public boolean addBook(BookDTO bookDTO){
        try{
            queue.add(bookDTO);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
