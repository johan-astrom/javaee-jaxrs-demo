package se.iths.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class StudentNotFoundWebException extends WebApplicationException {

    public StudentNotFoundWebException(Response response){
        super(response);
    };

    public StudentNotFoundWebException(String message, Response.Status status){
        super(message, status);
    }
}
