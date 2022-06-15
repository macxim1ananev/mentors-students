package com.example.mentorsstudents.event;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserActivateAfterRegistrationEvent extends ApplicationEvent {

    private String email;
    private Long userId;

    public UserActivateAfterRegistrationEvent(AfterSuccessRegistrationDto user) {
        super(user);
        email = user.getEmail();
        userId = user.getId();
    }
}
