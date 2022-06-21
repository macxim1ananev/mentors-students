package com.example.mentorsstudents.event;

import com.example.mentorsstudents.service.MailSenderService;
import com.example.mentorsstudents.util.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSettingListener implements ApplicationListener<ResetUserPasswordEvent> {

    private final MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(ResetUserPasswordEvent event) {
        this.resetUserPassword(event);
    }

    private void resetUserPassword(ResetUserPasswordEvent event) {
        String recipientAddress = event.getEmail();
        String subject = "Reset User Password";
        String confirmationUrl
                = "/users/settings/resetPassword/confirm/?token=" + event.getToken();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(Message.RESET_PASSWORD_LINK_SUCCESSFULLY_SEND + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSenderService.send(email);
    }
}
