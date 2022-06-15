package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.CheckUserRegistrationDto;
import com.example.mentorsstudents.dto.MessageResponse;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.event.UserActivateAfterRegistrationEvent;
import com.example.mentorsstudents.service.RegistrationService;
import com.example.mentorsstudents.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    private final VerificationService verificationService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AfterSuccessRegistrationDto userRegistration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        AfterSuccessRegistrationDto resultDto = registrationService.registrationUser(userRegistrationDto);
        eventPublisher.publishEvent(new UserActivateAfterRegistrationEvent(resultDto));
        return resultDto;
    }

    @GetMapping(path = "/confirm")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse confirmUserRegistration(@RequestParam String token) {
        return verificationService.confirmationUserRegistration(token);
    }

    @GetMapping("/{email}/")
    @ResponseStatus(HttpStatus.OK)
    public CheckUserRegistrationDto checkUserRegistration(@PathVariable("email") String email) {
        return registrationService.checkUserRegistration(email);
    }
}
