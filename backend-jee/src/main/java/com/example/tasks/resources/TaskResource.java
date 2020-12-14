package com.example.tasks.resources;

import com.example.tasks.boundary.TaskService;
import com.example.tasks.entity.Task;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.logging.Logger;


@Stateless
@Path("tasks/{name}")
public class TaskResource {

    public static final String NAME = "name";

    private static final Logger LOGGER = Logger.getLogger(TaskResource.class.getName());

    @EJB
    private TaskService taskService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("name") final String name) {
        LOGGER.info(MessageFormat.format("GET /task/{0}", name));
        final Task task = this.taskService.read(name);

        return Response.ok(task)
                .build();
    }

    @POST
    @Path("open")
    public Response open(@PathParam("name") final String name) {
        LOGGER.info(MessageFormat.format("POST /task/{0}/open", name));
        this.taskService.open(name);

        return Response.noContent().build();
    }

    @POST
    @Path("close")
    public Response close(@PathParam("name") final String name) {
        LOGGER.info(MessageFormat.format("POST /task/{0}/close", name));
        this.taskService.close(name);

        return Response.noContent().build();
    }
}
