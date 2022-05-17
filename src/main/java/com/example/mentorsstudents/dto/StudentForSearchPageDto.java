package com.example.mentorsstudents.dto;

import lombok.Value;

import java.util.Set;
@Value
public class StudentForSearchPageDto {

    ImageDto image;
    String firstName;
    String lastName;
    Set<SubjectDto> subjects;
}
