package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;


public interface RegistrationService {

    AfterSuccessRegistrationDto registrationUser(UserRegistrationDto userRegistrationDto);
}
