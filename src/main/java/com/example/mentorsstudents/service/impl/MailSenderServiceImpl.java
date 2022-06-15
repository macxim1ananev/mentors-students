package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {


    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void send(SimpleMailMessage mailMessage) {
        mailMessage.setFrom(username);
        mailSender.send(mailMessage);
    }
}
