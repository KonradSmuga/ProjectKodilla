package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTaskList", consumes = APPLICATION_JSON_VALUE)
    public List<TaskDto> getTask() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask/{taskId}", consumes = APPLICATION_JSON_VALUE)
    public TaskDto getTask(@PathVariable("taskId") Long taskId) {
        return taskMapper.mapToTaskDto(service.findById(taskId).get());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTasks", consumes = APPLICATION_JSON_VALUE)
    public void deleteTask(@RequestBody String taskId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTasks", consumes = APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {

        return new TaskDto((long) 1, "Edited test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
