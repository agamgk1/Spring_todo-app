package com.spring.course.controller;

import com.spring.course.model.Task;
import com.spring.course.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

// w testach end to end faktycznie uruchamia sie aplikacja i symulujemy akcje uzytkownika
// webEnviroment - umozliwia uruchomienie aplikacji na jakims porcie
// zmiana profilu na integration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {

    // wstrzykniecie nu,ery portu z webEnvironment
    @LocalServerPort
    private int port;

    //TestRestTemplate - klasa pozwalajaca na odpytanie istniejacych usług
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TaskRepository repo;

    @Test
    void httpGet_returnsAllTasks() {
        // given
        int initial = repo.findAll().size();
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));

        //when // symulacja wywolania listy wszystkich taskow
        Task[] result = restTemplate.getForObject("http:/localhost:" + port +"/tasks", Task[].class);

        //then
        assertThat(result).hasSize(initial+2);
    }
}