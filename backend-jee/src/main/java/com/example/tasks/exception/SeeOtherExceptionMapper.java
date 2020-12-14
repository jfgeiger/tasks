package com.example.tasks.exception;

import com.example.tasks.resources.TaskResource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Provider
public class SeeOtherExceptionMapper implements ExceptionMapper<SeeOtherException> {

    private static final Map<SeeOtherException.Type, BiFunction<SeeOtherException, UriBuilder, URI>> TYPES = new HashMap<>();

    static {
        TYPES.put(SeeOtherException.Type.TASK, SeeOtherExceptionMapper::getTaskUri);
    }

    private static URI getTaskUri(final SeeOtherException seeOtherException, final UriBuilder uriBuilder) {
        final String name = seeOtherException.getName();

        return uriBuilder
                .path(TaskResource.class)
                .resolveTemplate(TaskResource.NAME, name)
                .build();
    }

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(SeeOtherException e) {
        final Response.ResponseBuilder responseBuilder = Response.status(Response.Status.SEE_OTHER);
        final URI location = TYPES.get(e.getType())
                .apply(e, this.uriInfo.getBaseUriBuilder());
        responseBuilder.location(location);

        return responseBuilder.build();
    }
}
