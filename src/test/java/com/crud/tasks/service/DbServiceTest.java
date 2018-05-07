package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task task1 = new Task(1l, "title", "desc");
        Task task2 = new Task(2l, "title", "desc");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(dbService.getAllTasks()).thenReturn(tasks);
        //When
        List<Task> taskList = dbService.getAllTasks();
        //Then
        assertEquals(2, taskList.size());
    }

    @Test
    public void testGetTaskById() {
        //Given
        Task task1 = new Task(1L, "title", "content");
        Task task2 = new Task(2L, "title", "content");
        List<Task> tasks = Arrays.asList(task1, task2);

        when(dbService.findById(1L)).thenReturn(Optional.ofNullable(task1));
        when(dbService.findById(2L)).thenReturn(Optional.ofNullable(task2));
        //When
        Optional<Task> TaskById = dbService.findById(1l);
        //Then
        assertEquals(task1.getId(), TaskById.get().getId());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task1 = new Task(1l, "test", "test1");
        when(dbService.saveTask(task1)).thenReturn(task1);
        //When
        Task testTask = dbService.saveTask(task1);
        //Then
        assertEquals(task1.getId(), testTask.getId());
        assertEquals(task1.getTitle(), testTask.getTitle());
        assertEquals(task1.getContent(), testTask.getContent());
    }


}
