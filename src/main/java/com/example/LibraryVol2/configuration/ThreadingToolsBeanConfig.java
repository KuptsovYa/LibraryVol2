package com.example.LibraryVol2.configuration;

import com.example.LibraryVol2.dto.BookDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class ThreadingToolsBeanConfig {

    @Bean
    public LinkedBlockingQueue<BookDto> getQueue(){
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public ExecutorService getFixedThreadPool(@Value("${numOfThreads}") int numOfTh){ return Executors.newFixedThreadPool(numOfTh); }

}
