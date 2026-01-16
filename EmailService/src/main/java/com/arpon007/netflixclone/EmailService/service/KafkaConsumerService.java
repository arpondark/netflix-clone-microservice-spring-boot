package com.arpon007.netflixclone.EmailService.service;

import com.arpon007.netflixclone.EmailService.event.UserSignupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "user-signup", groupId = "email-service-group")
    public void consumeUserSignupEvent(UserSignupEvent event) {
        try {
            log.info("Received user signup event for email: {}", event.getEmail());
            sendWelcomeEmail(event.getEmail(), event.getFullName());
        } catch (Exception e) {
            log.error("Failed to process user signup event for email: {}", event.getEmail(), e);
        }
    }

    private void sendWelcomeEmail(String to, String fullName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Netflix Clone!");
            message.setText(String.format(
                    "Hello %s,\n\n" +
                            "Welcome to Netflix Clone! We're excited to have you on board.\n\n" +
                            "Your account has been successfully created. You can now log in and start exploring our content.\n\n"
                            +
                            "If you have any questions or need assistance, feel free to reach out to our support team.\n\n"
                            +
                            "Best regards,\n" +
                            "The Netflix Clone Team",
                    fullName));

            mailSender.send(message);
            log.info("Welcome email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send welcome email to: {}", to, e);
            throw e;
        }
    }
}
