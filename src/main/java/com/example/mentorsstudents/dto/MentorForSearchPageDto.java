package com.example.mentorsstudents.dto;

import lombok.Value;

import java.util.Set;

@Value
public class MentorForSearchPageDto {

    ImageDto image;
    String firstName;
    String lastName;
    Set<SubjectDto> subjects;
}
