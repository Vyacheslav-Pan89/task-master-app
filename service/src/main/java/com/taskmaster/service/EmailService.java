package com.taskmaster.service;

import com.taskmaster.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(CustomUser user) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Account Activation - Task Master");
        message.setText("Click to activate your account: http://localhost:8080/registration/activation/" + user.getActivationToken().getToken());
        mailSender.send(message);

    }
}
