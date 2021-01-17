package com.spring.course.controller;

import com.spring.course.model.Task;
import com.spring.course.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(TaskController.class)
class TaskControllerLightIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskRepository repo;

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        //given
        String desctription = "foo";
        when(repo.findById(anyInt()))
                .thenReturn(Optional.of(new Task(desctription, LocalDateTime.now())));


        //when + then
        mockMvc.perform(get("/tasks/"+ 123))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString(desctription)));
    }
}
