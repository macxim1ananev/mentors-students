package com.example.mentorsstudents.dto;

import com.example.mentorsstudents.entity.User;
import lombok.Value;

@Value
public class AboutUserDto {

    Long id;
    String textAboutUser;
    User user;
}
