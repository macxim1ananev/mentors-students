package com.example.mentorsstudents.dto;

import lombok.Value;

@Value
public class UserAuthRequestDto {

    String login;
    String password;
}
