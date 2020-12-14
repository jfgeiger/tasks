package com.example.tasks.controller;

import com.example.tasks.boundary.TasksService;
import com.example.tasks.entity.Task;
import com.example.tasks.exception.TaskAlreadyExistingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/tasks")
public class TasksController {

    private static final Logger LOGGER = Logger.getLogger(TasksController.class.getName());

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public List<Task> read() {
        LOGGER.info("GET /tasks");
        return this.tasksService.read();
    }

    @PostMapping(consumes = "application/json")
    public void create(@RequestBody final TaskDTO taskDTO) {
        final String name = taskDTO.getName();
        LOGGER.info(MessageFormat.format("POST /tasks ({0})", name));
        this.tasksService.create(name);
    }

    @ExceptionHandler({TaskAlreadyExistingException.class})
    public ResponseEntity<Void> handleTaskAlreadyExistingException(TaskAlreadyExistingException taskAlreadyExistingException) {
        final String path = TasksController.getReplacedPath(ServletUriComponentsBuilder.fromCurrentRequestUri());
        final String name = taskAlreadyExistingException.getName();
        final String location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .replacePath(path)
                .build(name)
                .toString();

        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .header(HttpHeaders.LOCATION, location)
                .build();
    }

    private static String getReplacedPath(ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final RequestMapping sourceRequestMapping = Objects.requireNonNull(TasksController.class.getAnnotation(RequestMapping.class));
        final RequestMapping targetRequestMapping = Objects.requireNonNull(TasksController.class.getAnnotation(RequestMapping.class));

        final String relativeSourcePath = String.join("", sourceRequestMapping.value());
        final String relativeTargetPath = String.join("", targetRequestMapping.value());
        final String absolutePath = Objects.requireNonNull(servletUriComponentsBuilder.build().getPath());

        return absolutePath.replace(relativeSourcePath, relativeTargetPath);
    }
}
