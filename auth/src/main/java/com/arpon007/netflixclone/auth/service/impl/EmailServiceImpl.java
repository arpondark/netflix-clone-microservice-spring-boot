package com.arpon007.netflixclone.auth.service.impl;

import com.arpon007.netflixclone.auth.feign.EmailFeignClient;
import com.arpon007.netflixclone.auth.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailFeignClient emailFeignClient;

    @Override
    public void sendVarificationEmail(String toEmail, String token) {
        String subject = "Account Verification";
        String body = "Please verify your email using this token: " + token;
        emailFeignClient.sendEmail(toEmail, subject, body);
    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String token) {
        String subject = "Password Reset Request";
        String body = "To reset your password, use this token: " + token;
        emailFeignClient.sendEmail(toEmail, subject, body);
    }
}
