package com.example.mentorsstudents.dto;

import com.example.mentorsstudents.entity.AboutUser;
import com.example.mentorsstudents.entity.Image;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import lombok.Value;

@Value
public class UserDto {

    Long id;
    String firstName;
    String lastName;
    Integer age;
    String email;
    Image image;
    AboutUser aboutUser;
    UserRole userRole;
}
