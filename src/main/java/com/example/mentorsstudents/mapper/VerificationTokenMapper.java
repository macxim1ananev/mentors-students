package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.entity.VerificationToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VerificationTokenMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "token", target = "token")
    VerificationToken toVerificationToken(Long userId, String token);
}
