package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.*;
import com.example.mentorsstudents.event.UserActivateAfterRegistrationEvent;
import com.example.mentorsstudents.service.RegistrationService;
import com.example.mentorsstudents.service.VerificationService;
import com.example.mentorsstudents.service.exception.UserRegistrationVerificationTokenException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/register")
@RequiredArgsConstructor
@Tag(name = "Registration controller", description = "API for registration users")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final VerificationService verificationService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registration new user", description = "Registration new user in application " +
            "and send confirmation registration token for email")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User has been successfully registered", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfterSuccessRegistrationDto.class))
            })
    })
    public AfterSuccessRegistrationDto userRegistration(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        AfterSuccessRegistrationDto resultDto = registrationService.registrationUser(userRegistrationDto);
        eventPublisher.publishEvent(new UserActivateAfterRegistrationEvent(resultDto));
        return resultDto;
    }

    @GetMapping(path = "/confirm")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Confirmation user registration", description = "after registration, the user receives " +
            "a message with a token for confirmation. by clicking on which, he confirms his registration " +
            "and his status goes to UserStatus.ACTIVE")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registration completed successfully.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageResponse.class))
            }),
            @ApiResponse(responseCode = "409",
                    description = "Token for confirmation registration has benn expired or invalid.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRegistrationVerificationTokenException.class))
            })
    })
    public MessageResponse confirmUserRegistration(@RequestParam String token) {
        return verificationService.confirmationUserRegistration(token);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check user registration", description = "Check if email has been registered in the mentors-students")
    @ApiResponse(responseCode = "200", description = "If user registered, method return id, email and user status. " +
            "Else return email user status not registered", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CheckUserRegistrationDto.class))
    })
    public CheckUserRegistrationDto checkUserRegistration(@RequestParam String email) {
        return registrationService.checkUserRegistration(email);
    }
}
