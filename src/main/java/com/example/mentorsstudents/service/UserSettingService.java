package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.ChangePasswordDto;
import com.example.mentorsstudents.dto.CheckUserPasswordResetTokenDto;
import com.example.mentorsstudents.dto.MessageResponse;
import com.example.mentorsstudents.entity.PasswordResetToken;

public interface UserSettingService {

    MessageResponse updatePassword(Long userId, ChangePasswordDto changePasswordDto);

    PasswordResetToken createResetPasswordTokenByUserEmail(String userEmail);

    MessageResponse confirmUserPasswordResetToken(CheckUserPasswordResetTokenDto checkUserPasswordResetTokenDto);
}
