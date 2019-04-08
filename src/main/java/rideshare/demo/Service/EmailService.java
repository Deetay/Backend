package rideshare.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailService {


    public JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    public void sendMessage(String emailAddress, String subject, String text) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailAddress);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);


    }
    public void sendMessageConcurrency(String emailAddress, String subject, String text){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> sendMessage(emailAddress, subject, text));



    }

}