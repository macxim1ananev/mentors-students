package com.example.mentorsstudents.service;

import com.example.mentorsstudents.entity.User;

public interface UserService {

    User loadUserByLogin(String login);
}
