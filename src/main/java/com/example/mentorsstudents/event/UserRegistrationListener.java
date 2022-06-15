package com.example.mentorsstudents.event;

import com.example.mentorsstudents.service.MailSenderService;
import com.example.mentorsstudents.service.VerificationService;
import com.example.mentorsstudents.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserRegistrationListener implements ApplicationListener<UserActivateAfterRegistrationEvent> {

    private final MailSenderService mailSenderService;
    private final VerificationService verificationService;

    @Override
    public void onApplicationEvent(UserActivateAfterRegistrationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(UserActivateAfterRegistrationEvent event) {
        String token = UUID.randomUUID().toString();
        verificationService.createVerificationToken(event.getUserId(), token);

        String recipientAddress = event.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = "/register/confirm/?token=" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(Message.CONFIRM_REGISTRATION_FOR_NEW_USER + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSenderService.send(email);
    }
}
