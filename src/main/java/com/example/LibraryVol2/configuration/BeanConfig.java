package com.example.LibraryVol2.configuration;

import com.example.LibraryVol2.dto.BookDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class BeanConfig {

    @Bean
    public LinkedBlockingQueue<BookDTO> getQueue(){
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public ExecutorService getFixedThreadPool(){
        return Executors.newFixedThreadPool(5);
    }
}
