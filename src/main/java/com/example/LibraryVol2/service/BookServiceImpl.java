package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service("BookService")
public class BookServiceImpl implements BookService {

    private LinkedBlockingQueue<BookDto> queue;

    @Autowired
    public BookServiceImpl(LinkedBlockingQueue<BookDto> queue) {
        this.queue = queue;
    }

    public void addBook(BookDto bookDTO){
        queue.add(bookDTO);
    }
}
