package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.BookDto;
import com.example.LibraryVol2.repository.BookRepository;
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
        List<Map<String, Object>> result = bookRepository.getAllBooks();
        List<String> resultNew = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            for (Map.Entry<String, Object> entry : result.get(i).entrySet()) {
                resultNew.add(entry.getValue().toString());
            }
        }

        int cols = 3, cnt = 0;
        String[][] values = new String[result.size()][cols];
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < cols; j++) {
                values[i][j] = resultNew.get(cnt++);
            }
        }

        return values;
    }
}
