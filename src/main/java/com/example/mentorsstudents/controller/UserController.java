package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<AfterSuccessRegistrationDto> userRegistration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        return new ResponseEntity<>(registrationService.registrationUser(userRegistrationDto), HttpStatus.CREATED);
    }
}
