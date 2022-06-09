package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.UserDto;
import com.example.mentorsstudents.entity.User;

public interface UserService {

    User loadUserByLogin(String login);

    UserDto findUserById(Long id);
}
