package se.iths.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EntityNotFoundWebException extends WebApplicationException {

    public EntityNotFoundWebException(String message, Response.Status status){
        super(message, status);
    }
}
