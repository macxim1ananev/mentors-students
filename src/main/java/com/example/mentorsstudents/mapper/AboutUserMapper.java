package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.dto.AboutUserDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.entity.AboutUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AboutUserMapper {

    AboutUserDto toDto(AboutUser aboutUser);

    AboutUser toAboutUser(AboutUserDto aboutUserDto);

    @Mapping(target = "textAboutUser",
            expression = "java(String.valueOf(dto.getAboutUser()))")
    AboutUser toAboutUserFromUserRegistrationDto(UserRegistrationDto dto);
}
