package kg.kazbekov.megatesttask.exception.server;

public class MessageNotSendedException extends BaseServerException {

    public MessageNotSendedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageNotSendedException() {
    }
}
