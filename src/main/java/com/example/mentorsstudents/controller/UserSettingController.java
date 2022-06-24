package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.ChangePasswordDto;
import com.example.mentorsstudents.dto.CheckUserPasswordResetTokenDto;
import com.example.mentorsstudents.dto.MessageResponse;
import com.example.mentorsstudents.entity.PasswordResetToken;
import com.example.mentorsstudents.event.ResetUserPasswordEvent;
import com.example.mentorsstudents.service.UserSettingService;
import com.example.mentorsstudents.service.exception.UserNotFoundException;
import com.example.mentorsstudents.service.exception.UserRegistrationVerificationTokenException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import liquibase.pro.packaged.A;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/users/settings")
@RequiredArgsConstructor
@Tag(name = "User settings controller", description = "API for settings users")
public class UserSettingController {

    private final UserSettingService userSettingService;
    private final ApplicationEventPublisher eventPublisher;

    @PatchMapping("/password/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change for user with id.", description = "Request for change user password by id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password has been successfully updated.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found or incorrect password.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation =MessageResponse.class))
            })
    })
    public MessageResponse changePassword(@PathVariable Long userId,
                                          @Valid @RequestBody ChangePasswordDto changePasswordDto) {
        return userSettingService.updatePassword(userId, changePasswordDto);
    }

    @PostMapping("/resetPassword")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Recover password by email.", description = "The user enters the email and receives a link " +
            "with a token to reset the password.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "A message with a link to reset your password " +
                    "has been sent to your email address.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User with this email not found.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserNotFoundException.class))
            })
    })
    public void resetPassword(@RequestParam("email") String userEmail) {
        PasswordResetToken passwordResetToken = userSettingService.createResetPasswordTokenByUserEmail(userEmail);
        eventPublisher.publishEvent(new ResetUserPasswordEvent(passwordResetToken, userEmail));
    }

    @PostMapping("/resetPassword/confirm")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Confirmation of registration.", description = "Confirmation of registration " +
            "and activation of the user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password has been successfully updated.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Invalid token", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRegistrationVerificationTokenException.class))
            })
    })
    public MessageResponse confirmPasswordResetToken(
            @RequestBody CheckUserPasswordResetTokenDto checkUserPasswordResetTokenDto){
        return userSettingService.confirmUserPasswordResetToken(checkUserPasswordResetTokenDto);
    }
}
