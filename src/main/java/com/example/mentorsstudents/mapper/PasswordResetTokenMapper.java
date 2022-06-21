package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.dto.CheckUserPasswordResetTokenDto;
import com.example.mentorsstudents.entity.PasswordResetToken;
import com.example.mentorsstudents.entity.VerificationToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PasswordResetTokenMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "token", target = "token")
    PasswordResetToken toPasswordResetToken(Long userId, String token);
}
