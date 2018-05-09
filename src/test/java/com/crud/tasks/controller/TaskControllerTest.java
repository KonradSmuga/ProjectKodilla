package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldFetchEmptyList() throws Exception {
        //Given
        List<TaskDto> taskDto = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDto);
        when(dbService.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }


    @Test
    public void shouldFetchGetTaskId() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(dbService.findById(1L)).thenReturn(Optional.of(new Task()));

        //When & Then
        mockMvc.perform(get("/v1/task/getTaskId/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1L)))
        .andExpect(jsonPath("$.title",is("title")));
    }


    @Test
    public void shouldFetchTask() throws Exception {

    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l, "title", "content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}
/*
    @Test
    public void shouldUpdateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1l, "title", "content");
        TaskDto updatedTask = new TaskDto(1l, "title2", "content2");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(updatedTask);

        Gson gson = new Gson();
        String cont = gson.toJson(taskDto);

        // When &  Then
        mockMvc.perform(put("/v1/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(cont))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title2")))
                .andExpect(jsonPath("$.content", is("content2")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l, "title", "content");

        //When & Then
        mockMvc.perform(delete("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
*/

