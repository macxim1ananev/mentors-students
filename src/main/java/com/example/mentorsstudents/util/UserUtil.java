package com.example.mentorsstudents.util;

import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User findByLogin(String login){
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unknown user %s ", login)));
    }
}
