package com.example.tasks.resources;

import com.example.tasks.boundary.TasksService;
import com.example.tasks.entity.Task;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Path("tasks")
public class TasksResource {

    private static final Logger LOGGER = Logger.getLogger(TasksResource.class.getName());

    @EJB
    private TasksService tasksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response read() {
        LOGGER.info("GET /tasks");
        final List<Task> tasks = this.tasksService.read();

        return Response.ok(tasks)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final TaskDTO taskDTO) {
        final String name = taskDTO.getName();
        LOGGER.info(MessageFormat.format("POST /tasks ({0})", name));
        this.tasksService.create(name);

        return Response.noContent().build();
    }
}
