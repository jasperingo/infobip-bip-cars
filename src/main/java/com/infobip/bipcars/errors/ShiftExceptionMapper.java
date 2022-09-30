package com.infobip.bipcars.errors;

import lombok.Value;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class ShiftExceptionMapper implements ExceptionMapper<Exception> {

    @Value
    private static class Error {
        String error;
    }

    @Override
    public Response toResponse(final Exception exception) {

        Response.ResponseBuilder responseBuilder;

        if (exception instanceof NotFoundException) {
            responseBuilder = Response.status(Response.Status.NOT_FOUND);
        } else {
            responseBuilder = Response.serverError();
        }

        Logger.getLogger(ShiftExceptionMapper.class.getName()).log(Level.SEVERE, null, exception);

        return responseBuilder
                .entity(new Error(exception.getMessage()))
                .build();
    }
}
