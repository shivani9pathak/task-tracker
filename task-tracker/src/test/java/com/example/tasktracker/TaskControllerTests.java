package com.example.tasktracker;

import com.example.tasktracker.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTests {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @Test
    void createAndGetTask() throws Exception {
        Task t = new Task();
        t.setTitle("Test Task");
        t.setDescription("From JUnit");

        // Create
        String json = om.writeValueAsString(t);
        String location = mvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        // List
        mvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }
}