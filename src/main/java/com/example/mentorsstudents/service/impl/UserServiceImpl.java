package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User loadUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unknown user login %s", login)));
    }
}
