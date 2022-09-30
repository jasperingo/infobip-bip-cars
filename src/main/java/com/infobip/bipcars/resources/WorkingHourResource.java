package com.infobip.bipcars.resources;

import com.infobip.bipcars.entities.WorkingHour;
import com.infobip.bipcars.repositories.WorkingHourRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/working-hours")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkingHourResource {
    @Inject
    private WorkingHourRepository workingHourRepository;

    @POST
    public Response create(@NotNull @Valid final WorkingHour workingHour) {
        workingHourRepository.create(workingHour);

        return Response.created(URI.create(""))
                .entity(workingHour)
                .build();
    }

    @GET
    public Response findMany() {
        return Response.ok(workingHourRepository.findMany()).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") final Long id, @NotNull @Valid final WorkingHour workingHour) {
        final WorkingHour existingWorkingHour = workingHourRepository.find(id);

        if (existingWorkingHour == null) {
            throw new NotFoundException("Working hour not found");
        }

        workingHour.setId(existingWorkingHour.getId());

        workingHourRepository.update(workingHour);

        return Response.ok(workingHour).build();
    }

    @GET
    @Path("/{id}")
    public Response findOne(@PathParam("id") final Long id) {
        final WorkingHour workingHour = workingHourRepository.find(id);

        if (workingHour == null) {
            throw new NotFoundException("Working hour not found");
        }

        return Response.ok(workingHour).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Long id) {
        final WorkingHour workingHour = workingHourRepository.find(id);

        if (workingHour == null) {
            throw new NotFoundException("Working hour not found");
        }

        workingHourRepository.delete(workingHour);

        return Response.noContent().build();
    }
}
