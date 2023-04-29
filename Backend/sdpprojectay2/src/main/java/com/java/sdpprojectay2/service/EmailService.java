package com.java.sdpprojectay2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;


@Component
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("Mail sent");
    }

    // This is test for checking mail service
    @Autowired
    private EmailService emailService;

    public void sendEmail() throws MessagingException {
        String to = "ibrahimsadiqov2000@gmail.com";
        String subject = "Test Email";
        String text = "This is a test email sent from Spring Boot";
        emailService.sendSimpleMessage(to, subject, text);
    }
}
