package kg.kazbekov.megatesttask.service.external;

import kg.kazbekov.megatesttask.exception.server.MessageNotSendedException;

public interface EmailService {
    void sendTaskCreatedEmail(String to, String subject, String body) throws MessageNotSendedException;
}
