package com.example.mentorsstudents.security;

import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.service.UserService;
import com.example.mentorsstudents.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.loadUserByLogin(login);

        return org.springframework.security.core.userdetails.User
                .builder().username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getUserRole().toString())
                .build();
    }
}
