package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.entity.AboutUser;
import com.example.mentorsstudents.entity.Image;
import com.example.mentorsstudents.entity.Subject;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.mapper.AboutUserMapper;
import com.example.mentorsstudents.mapper.ImageMapper;
import com.example.mentorsstudents.mapper.SubjectMapper;
import com.example.mentorsstudents.mapper.UserMapper;
import com.example.mentorsstudents.repository.SubjectRepository;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    private final UserMapper userMapper;
    private final AboutUserMapper aboutUserMapper;
    private final SubjectMapper subjectMapper;
    private final ImageMapper imageMapper;

    @Override
    public AfterSuccessRegistrationDto registrationUser(UserRegistrationDto userRegistrationDto) {

        User user = userRepository.save(userRegistrationMapper(userRegistrationDto));
        return userMapper.toDtoAfterRegistrationUer(user, user.getImage(), user.getAboutUser(), user.getSubjects());
    }

    private User userRegistrationMapper(UserRegistrationDto userRegistrationDto) {
        Image image = imageMapper.toImageFromUserRegistrationDto(userRegistrationDto.getImage());
        AboutUser aboutUser = aboutUserMapper.toAboutUserFromUserRegistrationDto(userRegistrationDto);
        User user = userMapper.toUserFromUserRegistrationDto(userRegistrationDto, image, aboutUser,
                userRegistrationDto.getSubjects());
        return user;
    }

}
