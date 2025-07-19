package kg.kazbekov.megatesttask.service.external.impl;


import kg.kazbekov.megatesttask.exception.server.MessageNotSendedException;
import kg.kazbekov.megatesttask.service.external.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public void sendTaskCreatedEmail(String to, String subject, String body) throws MessageNotSendedException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            throw new MessageNotSendedException("An error occurred while sending message to email", e);
        }
        log.info("Task creation email sent to {}", to);

    }
}
