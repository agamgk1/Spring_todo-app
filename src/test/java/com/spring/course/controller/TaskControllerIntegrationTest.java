package com.spring.course.controller;

import com.spring.course.model.Task;
import com.spring.course.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//AutoConfiureMockMvs - adnotacja dla testow integracyjnych
//MockMvc - klasa ułatwiajaca testy integracyjne
@SpringBootTest
@AutoConfigureMockMvc
 @ActiveProfiles("integration")
class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repo;

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        //given
        int id = repo.save(new Task("foo", LocalDateTime.now())).getId();

        //when + then
        mockMvc.perform(get("/tasks/"+ id))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
    @Test
    void httpGet_returnsAllTasks() throws Exception {
        //given
        int id = repo.save(new Task("foo", LocalDateTime.now())).getId();

        //when + then
        mockMvc.perform(get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
