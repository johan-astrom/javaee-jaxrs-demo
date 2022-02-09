package se.iths.exception;

import javax.ws.rs.core.Response;

public class StudentErrorMessage {

    private String message;
    private Response.Status status;

    public StudentErrorMessage(String message, Response.Status status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentErrorMessage{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
