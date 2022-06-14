package com.example.mentorsstudents.util;

import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.exception.ErrorMessage;
import com.example.mentorsstudents.service.exception.PasswordMismatchException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByLogin(String login){
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unknown user %s ", login)));
    }

    public void checkPassword(@NonNull String password, @NonNull String storedPassword) {
        Optional.ofNullable(storedPassword)
                .filter(p -> encoder.matches(password, p))
                .orElseThrow(() -> new PasswordMismatchException(ErrorMessage.AUTH_FAILED_MESS_INCORRECT_PASSWORD));
    }
}
