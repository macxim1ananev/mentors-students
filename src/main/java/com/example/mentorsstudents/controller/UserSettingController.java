package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.ChangePasswordDto;
import com.example.mentorsstudents.dto.CheckUserPasswordResetTokenDto;
import com.example.mentorsstudents.dto.MessageResponse;
import com.example.mentorsstudents.entity.PasswordResetToken;
import com.example.mentorsstudents.event.ResetUserPasswordEvent;
import com.example.mentorsstudents.service.UserSettingService;
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
public class UserSettingController {

    private final UserSettingService userSettingService;
    private final ApplicationEventPublisher eventPublisher;

    @PatchMapping("/password/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse changePassword(@PathVariable Long userId,
                                          @Valid @RequestBody ChangePasswordDto changePasswordDto) {
        return userSettingService.updatePassword(userId, changePasswordDto);
    }

    @PostMapping("/resetPassword")
    public void resetPassword(@RequestParam("email") String userEmail) {
        PasswordResetToken passwordResetToken = userSettingService.createResetPasswordTokenByUserEmail(userEmail);
        eventPublisher.publishEvent(new ResetUserPasswordEvent(passwordResetToken, userEmail));
    }

    @PostMapping("/resetPassword/confirm")
    public MessageResponse confirmPasswordResetToken(
            @RequestBody CheckUserPasswordResetTokenDto checkUserPasswordResetTokenDto){
        return userSettingService.confirmUserPasswordResetToken(checkUserPasswordResetTokenDto);
    }
}
