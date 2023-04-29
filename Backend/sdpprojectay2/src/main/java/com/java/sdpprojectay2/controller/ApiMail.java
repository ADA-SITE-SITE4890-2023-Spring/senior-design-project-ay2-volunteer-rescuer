package com.java.sdpprojectay2.controller;

import com.java.sdpprojectay2.dto.Response;
import com.java.sdpprojectay2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/mobile/volunteer-rescue-project/mail")
public class ApiMail {

    @Autowired
    EmailService emailService;

    @PostMapping(value = "/send")
    public Response sendMail() throws MessagingException {
        emailService.sendEmail();
        return new Response();
    }
}
