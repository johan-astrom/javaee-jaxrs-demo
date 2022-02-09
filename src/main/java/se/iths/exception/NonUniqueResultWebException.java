package se.iths.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NonUniqueResultWebException extends WebApplicationException {

    public NonUniqueResultWebException(String message, Response.Status status) {
        super(Response.status(status)
                .entity(new StudentErrorMessage(message, status))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
