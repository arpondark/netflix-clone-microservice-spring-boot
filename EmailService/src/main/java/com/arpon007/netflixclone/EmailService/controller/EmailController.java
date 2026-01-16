package com.arpon007.netflixclone.EmailService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
}
