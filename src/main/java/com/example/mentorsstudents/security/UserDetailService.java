package com.example.mentorsstudents.security;

import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserUtil userUtil;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userUtil.findByLogin(login);

        return org.springframework.security.core.userdetails.User
                .builder().username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getUserRole().toString())
                .build();
    }
}
