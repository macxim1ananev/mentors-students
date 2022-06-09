package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.CheckUserRegistrationDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AfterSuccessRegistrationDto userRegistration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        return registrationService.registrationUser(userRegistrationDto);
    }

    @GetMapping("/{email}/")
    @ResponseStatus(HttpStatus.OK)
    public CheckUserRegistrationDto checkUserRegistration(@PathVariable("email") String email) {
        return registrationService.checkUserRegistration(email);
    }
}
