package com.example.mentorsstudents.dto;

import com.example.mentorsstudents.entity.User;
import lombok.Value;

@Value
public class AboutUserDto {

    Long aboutUserId;
    String textAboutUser;
}
