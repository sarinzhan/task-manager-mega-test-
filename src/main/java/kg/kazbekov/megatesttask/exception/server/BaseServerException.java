package kg.kazbekov.megatesttask.exception.server;

public class BaseServerException extends Exception{
    public BaseServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseServerException(String message) {
        super(message);
    }

    public BaseServerException() {
    }
}
