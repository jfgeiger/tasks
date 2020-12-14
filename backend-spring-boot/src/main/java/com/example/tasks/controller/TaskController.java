package com.example.tasks.controller;

import com.example.tasks.boundary.TaskService;
import com.example.tasks.entity.Task;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Logger;


@RestController
@RequestMapping("api/tasks/{name}")
public class TaskController {

    private static final Logger LOGGER = Logger.getLogger(TaskController.class.getName());

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Task read(@PathVariable("name") final String name) {
        LOGGER.info(MessageFormat.format("GET /task/{0}", name));
        return this.taskService.read(name);
    }

    @PostMapping("open")
    public void open(@PathVariable("name") final String name) {
        LOGGER.info(MessageFormat.format("POST /task/{0}/open", name));
        this.taskService.open(name);
    }

    @PostMapping("close")
    public void close(@PathVariable("name") final String name) {
        LOGGER.info(MessageFormat.format("POST /task/{0}/close", name));
        this.taskService.close(name);
    }
}
