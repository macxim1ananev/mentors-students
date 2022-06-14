package com.example.mentorsstudents.dto;

import com.example.mentorsstudents.validation.annotation.Name;
import com.example.mentorsstudents.validation.annotation.UserAge;
import lombok.Value;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Value
public class UserDtoForUpdate {

    @Name
    String firstName;
    @Name
    String lastName;
    @UserAge
    Integer age;
    @Email(message = "Must be in the format of an email address")
    String email;
    ImageDto image;
    @Size(min = 20, max = 500, message = "Size must be available from 20 to 500")
    AboutUserDto aboutUser;
    @NotNull
    Set<SubjectDto> subjects;
    @NotNull
    String userRole;
}
