package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.CheckUserRegistrationDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;

public interface RegistrationService {

    AfterSuccessRegistrationDto registrationUser(UserRegistrationDto userRegistrationDto);

    CheckUserRegistrationDto checkUserRegistration(String email);
}
