package se.iths.exception;

public class StudentErrorMessage {

    private String message;
    private int status;

    public StudentErrorMessage(String message, int status) {
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
