package com.example.mentorsstudents.service;

import org.springframework.mail.SimpleMailMessage;

public interface MailSenderService {

    void send(SimpleMailMessage mailMessage);

}
