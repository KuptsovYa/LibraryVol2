package com.example.LibraryVol2.configuration;

import com.example.LibraryVol2.dto.BookDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@EnableAsync
public class BeanConfig {

    @Bean
    public LinkedBlockingQueue<BookDTO> getQueue(){
        return new LinkedBlockingQueue<>();
    }

    @Bean(name = "fixedThreadPool")
    public ExecutorService getFixedThreadPool(){
        return Executors.newFixedThreadPool(5);
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();
        return executor;
    }

}
