package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.repository.BookRepository;
import org.apache.logging.log4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

@Service("BookService")
public class BookServiceImpl implements BookService {

    private LinkedBlockingQueue<BookDto> queue;
    private BookRepository bookRepository;
    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public BookServiceImpl(LinkedBlockingQueue<BookDto> queue, BookRepository bookRepository) {
        this.queue = queue;
        this.bookRepository = bookRepository;
    }

    public void addBook(BookDto bookDTO) {
        queue.add(bookDTO);
    }

    @Override
    public String[][] getAllBooks() {
        logger.info("Getting all books on user profile");
        String[][] values;
        try {
            List<Map<String, Object>> result = bookRepository.getAllBooks();
            List<String> resultNew = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                for (Map.Entry<String, Object> entry : result.get(i).entrySet()) {
                    resultNew.add(entry.getValue().toString());
                }
            }

            int cols = 3, cnt = 0;
            values = new String[result.size()][cols];
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < cols; j++) {
                    values[i][j] = resultNew.get(cnt++);
                }
            }
            logger.info("Improper words are taken from database");
            return values;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return values = new String[0][0];
    }
}
