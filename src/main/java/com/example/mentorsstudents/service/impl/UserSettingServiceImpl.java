package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.ChangePasswordDto;
import com.example.mentorsstudents.dto.CheckUserPasswordResetTokenDto;
import com.example.mentorsstudents.dto.MessageResponse;
import com.example.mentorsstudents.entity.PasswordResetToken;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.mapper.PasswordResetTokenMapper;
import com.example.mentorsstudents.repository.ResetPasswordTokenRepository;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.UserSettingService;
import com.example.mentorsstudents.service.exception.*;
import com.example.mentorsstudents.util.Message;
import com.example.mentorsstudents.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSettingServiceImpl implements UserSettingService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;

    private final PasswordResetTokenMapper passwordResetTokenMapper;

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;


    @Override
    @Transactional
    public MessageResponse updatePassword(Long userId, ChangePasswordDto changePasswordDto) {
        userUtil.checkPassword(changePasswordDto.getPassword(), getStoredPassword(userId));
        userRepository.updatePasswordByUserId(userId, passwordEncoder.encode(changePasswordDto.getNewPassword()));
        return new MessageResponse(Message.PASSWORD_CHANGED);
    }

    @Override
    public PasswordResetToken createResetPasswordTokenByUserEmail(String userEmail) {
        User user = userRepository.findByLogin(userEmail).orElseThrow(
                () -> new UserNotFoundException(String.format("Unknown user %s ", userEmail)));

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = passwordResetTokenMapper.toPasswordResetToken(user.getUserId(), token);
        passwordResetToken.setExpiryDate(1440);
        return resetPasswordTokenRepository.save(passwordResetToken);
    }

    @Override
    @Transactional
    public MessageResponse confirmUserPasswordResetToken(CheckUserPasswordResetTokenDto passwordResetTokenDto) {
        PasswordResetToken passwordResetToken = getPasswordResetToken(passwordResetTokenDto.getToken());
        Calendar calendar = Calendar.getInstance();
        if (passwordResetToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0){
            throw new UserRegistrationVerificationTokenException(ErrorMessage.TOKEN_HAS_EXPIRED);
        }
        userRepository.updatePasswordByUserId(passwordResetToken.getUserId()
                , passwordEncoder.encode(passwordResetTokenDto.getNewPassword()));
        return new MessageResponse(Message.PASSWORD_CHANGED);
    }

    private PasswordResetToken getPasswordResetToken(String token) {
        return resetPasswordTokenRepository.getUserPasswordResetToken(token)
                .orElseThrow(() -> new UserResetPasswordException(ErrorMessage.RESET_PASSWORD_TOKEN_INVALID));
    }

    private String getStoredPassword(Long userId) {
        return userRepository.findPasswordByUserId(userId).orElseThrow(
                () -> new PasswordMismatchException(ErrorMessage.AUTH_FAILED_MESS_INCORRECT_PASSWORD));
    }
}
