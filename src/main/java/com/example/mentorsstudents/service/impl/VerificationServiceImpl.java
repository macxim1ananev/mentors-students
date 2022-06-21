package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.MessageResponse;
import com.example.mentorsstudents.entity.VerificationToken;
import com.example.mentorsstudents.entity.enumiration.UserStatus;
import com.example.mentorsstudents.mapper.VerificationTokenMapper;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.repository.VerificationTokenRepository;
import com.example.mentorsstudents.service.VerificationService;
import com.example.mentorsstudents.service.exception.ErrorMessage;
import com.example.mentorsstudents.service.exception.UserRegistrationVerificationTokenException;
import com.example.mentorsstudents.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;

    private final UserRepository userRepository;

    private final VerificationTokenMapper verificationTokenMapper;

    @Override
    @Transactional
    public void createVerificationToken(Long userId, String token) {
        VerificationToken verificationToken = verificationTokenMapper.toVerificationToken(userId, token);
        verificationToken.setExpiryDate(1440);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    @Transactional
    public MessageResponse confirmationUserRegistration(String token) {
        VerificationToken verificationToken = getVerificationToken(token);
        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0){
            throw new UserRegistrationVerificationTokenException(ErrorMessage.TOKEN_HAS_EXPIRED);
        }
        userRepository.setUserStatus(UserStatus.ACTIVE, verificationToken.getUserId());
        return new MessageResponse(Message.CONFIRM_REGISTRATION_SUCCESSFULLY);
    }
    @Scheduled(cron = "@daily")
    @Override
    @Transactional
    public void deleteOldVerificationTokens() {
        verificationTokenRepository.deleteOldVerificationTokens();
    }

    private VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.getUserVerificationToken(token).orElseThrow(
                () -> new UserRegistrationVerificationTokenException(ErrorMessage.INVALID_TOKEN_FOR_CONFIRM_REGISTRATION_USER));
    }
}
