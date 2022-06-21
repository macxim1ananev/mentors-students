package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.MessageResponse;

public interface VerificationService {
    void createVerificationToken(Long userId, String token);

    MessageResponse confirmationUserRegistration(String token);

    void deleteOldVerificationTokens();
}
