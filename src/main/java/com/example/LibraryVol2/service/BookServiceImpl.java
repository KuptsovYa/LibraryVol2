package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.dto.ConfigDto;
import com.example.LibraryVol2.repository.BookRepository;
import org.apache.logging.log4j.*;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void addBook(BookDto bookDTO) {
        queue.add(bookDTO);
    }

    @Override
    public String[][] getAllBooks(ConfigDto configDto) {
        logger.info("Getting all books on user profile");
        try {
            if(configDto.isPersonal()){
                configDto.setUserId(bookRepository.getIdByName(configDto.getUserName()));
                List<Map<String, Object>> result = bookRepository.getPersonalBooks(configDto);
                return createArrOfBooks(result);
            }else {
                List<Map<String, Object>> result = bookRepository.getAllBooks(configDto);
                return createArrOfBooks(result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new String[0][0];
    }

    @Override
    public String[][] createArrOfBooks(List<Map<String, Object>> list) {
        String[][] values;
        List<String> resultNew = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (Map.Entry<String, Object> entry : list.get(i).entrySet()) {
                resultNew.add(entry.getValue().toString());
            }
        }

        int cols = 3, cnt = 0;
        values = new String[list.size()][cols];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < cols; j++) {
                values[i][j] = resultNew.get(cnt++);
            }
        }
        logger.info("books successfully transferred from the db");
        return values;
    }

    @Override
    public String getBookContent(String title) {
        try {
            logger.info("trying to take book content of" + title);
            return bookRepository.getContentByTitle(title);
        }catch (Exception e){
            logger.error("book with title: " + title + " not found");
            return "";
        }
    }
}
