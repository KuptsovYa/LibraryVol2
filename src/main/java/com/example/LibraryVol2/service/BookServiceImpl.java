package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDTO;
import com.example.LibraryVol2.entity.Book;
import com.example.LibraryVol2.logic.Consumer;
import com.example.LibraryVol2.logic.Worker;
import com.example.LibraryVol2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

@Service("BookService")
public class BookServiceImpl implements BookService {

    private LinkedBlockingQueue<BookDTO> queue;
    private ExecutorService executorService;

//    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public BookServiceImpl(LinkedBlockingQueue<BookDTO> queue, ExecutorService executorService, ApplicationContext applicationContext) {
        this.executorService = executorService;
        this.queue = queue;
        this.applicationContext = applicationContext;
    }

    public boolean addBook(BookDTO bookDTO){
        try{
            queue.add(bookDTO);
//            Consumer consumer = applicationContext.getBean(Consumer.class);
//            executorService.submit(consumer, queue);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
