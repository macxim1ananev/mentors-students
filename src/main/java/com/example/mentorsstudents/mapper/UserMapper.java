package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.dto.*;
import com.example.mentorsstudents.entity.AboutUser;
import com.example.mentorsstudents.entity.Image;
import com.example.mentorsstudents.entity.Subject;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.mapper.annotation.BaseImageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.Set;


@Mapper(componentModel = "spring", uses = {SubjectMapper.class, ImageMapper.class, AboutUserMapper.class})

public interface UserMapper {

    @Mapping(source = "user.userId", target = "id")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.age", target = "age")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.image", target = "image")
    @Mapping(source = "user.aboutUser", target = "aboutUser")
    @Mapping(source = "user.userRole", target = "userRole")
    UserDto toDto(User user);

    User toUser(UserDto userDto);

    @Mapping(source = "dto.firstName", target = "firstName")
    @Mapping(source = "dto.lastName", target = "lastName")
    @Mapping(source = "dto.age", target = "age")
    @Mapping(source = "dto.email", target = "email")
    @Mapping(source = "dto.password", target = "password")
    @Mapping(source = "dto.userRole", target = "userRole")
    @Mapping(source = "dto.userStatus", target = "userStatus")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "aboutUser", target = "aboutUser")
    @Mapping(source = "subjects", target = "subjects")
    User toUserFromUserRegistrationDto(UserRegistrationDto dto, Image image, AboutUser aboutUser, Set<SubjectDto> subjects);

    @Mapping(source = "user.userId", target = "id")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.age", target = "age")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.password", target = "password")
    @Mapping(source = "image", target = "image")
    @Mapping(target = "aboutUser", expression = "java(String.valueOf(aboutUser.getTextAboutUser()))")
    @Mapping(source = "subjects", target = "subjects")
    @Mapping(source = "user.userRole", target = "userRole")
    AfterSuccessRegistrationDto toDtoAfterRegistrationUer(User user, Image image, AboutUser aboutUser, Set<Subject> subjects);

    @Mapping(source = "user.image", target = "image")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "subjects", target = "subjects")
    MentorForSearchPageDto toMentorForSearchPagDtoFromUser(User user);

    @Mapping(source = "user.image", target = "image")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "subjects", target = "subjects")
    StudentForSearchPageDto toStudentForSearchPageDtoFromUser(User user);
    @Mapping(source = "userDtoForUpdate.image", target = "existedUser.image", qualifiedBy = BaseImageInfo.class)
    User updateUser(UserDtoForUpdate userDtoForUpdate,  @MappingTarget User existedUser);
}
