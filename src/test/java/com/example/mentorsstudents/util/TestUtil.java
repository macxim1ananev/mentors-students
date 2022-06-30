package com.example.mentorsstudents.util;

import com.example.mentorsstudents.dto.ImageDto;
import com.example.mentorsstudents.dto.SubjectDto;
import com.example.mentorsstudents.entity.AboutUser;
import com.example.mentorsstudents.entity.Image;
import com.example.mentorsstudents.entity.Subject;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import com.example.mentorsstudents.entity.enumiration.UserStatus;

import java.util.HashSet;
import java.util.Set;

public class TestUtil {

    public static User getUser(UserRole userRole, UserStatus userStatus){
        User user = new User();
        user.setUserId(10L);
        user.setFirstName("User");
        user.setLastName("User");
        user.setAge("20");
        user.setEmail("test@mail.ru");
        user.setUserRole(userRole);
        user.setUserStatus(userStatus);
        return user;
    }

    /**
     * Create Image entity without setting User field
     * @return Image
     */
    public static Image getImage(){
        Image image = new Image();
        image.setImageId(10L);
        image.setLocation("test/location");
        return image;
    }

    /**
     * Create AboutUser entity without setting User field
     * @return AboutUser
     */
    public static AboutUser getAboutUser(){
        AboutUser aboutUser = new AboutUser();
        aboutUser.setAboutUserId(10L);
        aboutUser.setTextAboutUser("test text about user");
        return aboutUser;
    }

    public static ImageDto getImageDto() {
        ImageDto imageDto = new ImageDto(10L, "test/location");
        return imageDto;
    }

    public static Set<SubjectDto> getSetSubjectDto() {
        Set<SubjectDto> subjectDtos = new HashSet<>();
        subjectDtos.add(new SubjectDto(5L, "Javascript"));
        return subjectDtos;
    }

    public static Set<Subject> getSetSubjects() {
        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject(5L, "Javascript", getUsersSet()));
        return subjects;
    }

    private static Set<User> getUsersSet() {
        Set<User> users = new HashSet<>();
        users.add(getUser(UserRole.STUDENT, UserStatus.NOT_ACTIVE));
        return users;
    }
}
