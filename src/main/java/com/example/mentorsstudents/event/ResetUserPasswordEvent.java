package com.example.mentorsstudents.event;

import com.example.mentorsstudents.entity.PasswordResetToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class ResetUserPasswordEvent extends ApplicationEvent {

    private String email;
    private String token;

    public ResetUserPasswordEvent(PasswordResetToken passwordResetToken, String email) {
        super(passwordResetToken);
        this.email = email;
        this.token = passwordResetToken.getToken();
    }
}
