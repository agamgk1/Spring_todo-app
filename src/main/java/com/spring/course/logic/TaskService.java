package com.spring.course.logic;

import com.spring.course.model.Task;
import com.spring.course.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

//klasa służąca do asynchronicznej obsługi zapytań (uruchamiajac dodatkowy watek w tle)
@Service
public class TaskService {
    public static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository repository;

    TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    // Konieczna adnotacja Async
    // akcja odroczona w czasie ktora dzije sie na osobnej, wspoldzielonej puli watkow - CompletableFuture
    // +metoda supplyAsynch
    @Async
    public CompletableFuture<List<Task>> findAllAsync() {
        logger.info("Supply async");
        return CompletableFuture.supplyAsync(repository::findAll);
    }
}
