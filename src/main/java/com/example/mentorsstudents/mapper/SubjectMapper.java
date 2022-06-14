package com.example.mentorsstudents.mapper;

import com.example.mentorsstudents.dto.SubjectDto;
import com.example.mentorsstudents.entity.Subject;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDto toDto(Subject subject);

    Set<SubjectDto> toDtoSet(Set<Subject> subjects);

    Subject toSubject(SubjectDto subjectDto);
}
