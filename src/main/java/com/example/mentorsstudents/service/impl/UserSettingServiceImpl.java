package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.ChangePasswordDto;
import com.example.mentorsstudents.dto.MessageResponse;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.UserSettingService;
import com.example.mentorsstudents.service.exception.ErrorMessage;
import com.example.mentorsstudents.service.exception.PasswordMismatchException;
import com.example.mentorsstudents.util.Message;
import com.example.mentorsstudents.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSettingServiceImpl implements UserSettingService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;


    @Override
    @Transactional
    public MessageResponse updatePassword(Long userId, ChangePasswordDto changePasswordDto) {
        userUtil.checkPassword(changePasswordDto.getPassword(), getStoredPassword(userId));
        userRepository.updatePasswordByUserId(userId, passwordEncoder.encode(changePasswordDto.getNewPassword()));
        return new MessageResponse(Message.PASSWORD_CHANGED);
    }

    private String getStoredPassword(Long userId) {
        return userRepository.findPasswordByUserId(userId).orElseThrow(
                () -> new PasswordMismatchException(ErrorMessage.AUTH_FAILED_MESS_INCORRECT_PASSWORD));
    }
}
