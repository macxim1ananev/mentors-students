package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.UserDto;
import com.example.mentorsstudents.dto.UserDtoForUpdate;
import com.example.mentorsstudents.entity.User;

public interface UserService {

    User loadUserByLogin(String login);

    UserDto findUserById(Long id);

    void deleteUserById(Long id);

    UserDto updateUserById(Long id, UserDtoForUpdate user);
}
