package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.ChangePasswordDto;
import com.example.mentorsstudents.dto.MessageResponse;

public interface UserSettingService {

    MessageResponse updatePassword(Long userId, ChangePasswordDto changePasswordDto);
}
