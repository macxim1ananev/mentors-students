package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.UserDto;
import com.example.mentorsstudents.dto.UserDtoForUpdate;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.mapper.UserMapper;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.UserService;
import com.example.mentorsstudents.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    @Transactional(readOnly = true)
    public User loadUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Unknown user login %s", login)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with this ID not found %d", id)));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto updateUserById(Long id, UserDtoForUpdate user) {
        User userForUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with this ID not found %d", id)));
        User updatedUser = userMapper.updateUser(user, userForUpdate);
        return userMapper.toDto(userRepository.save(updatedUser));
    }
}
