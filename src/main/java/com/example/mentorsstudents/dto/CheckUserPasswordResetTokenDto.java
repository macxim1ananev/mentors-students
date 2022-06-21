package com.example.mentorsstudents.dto;

import lombok.Value;

@Value
public class CheckUserPasswordResetTokenDto {

    String token;
    String newPassword;

}
