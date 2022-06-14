package com.example.mentorsstudents.dto;

import com.example.mentorsstudents.entity.Image;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import lombok.Value;
import java.util.Set;

@Value
public class AfterSuccessRegistrationDto {

    Long id;
    String firstName;
    String lastName;
    Integer age;
    String email;
    String password;
    Image image;
    String aboutUser;
    Set<SubjectDto> subjects;
    UserRole userRole;
}
