package com.example.mentorsstudents.dto;


import com.example.mentorsstudents.validation.annotation.Password;
import lombok.Value;

@Value
public class ChangePasswordDto {

    @Password
    String password;

    @Password
    String newPassword;
}
