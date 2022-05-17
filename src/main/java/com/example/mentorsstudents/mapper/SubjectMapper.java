package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.dto.SubjectDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.entity.Subject;
import com.example.mentorsstudents.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDto toDto(Subject subject);

    Set<SubjectDto> toDtoSet(Set<Subject> subjects);

    Subject toSubject(SubjectDto subjectDto);


}
