package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.CheckUserRegistrationDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.entity.AboutUser;
import com.example.mentorsstudents.entity.Image;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import com.example.mentorsstudents.mapper.AboutUserMapper;
import com.example.mentorsstudents.mapper.ImageMapper;
import com.example.mentorsstudents.mapper.UserMapper;
import com.example.mentorsstudents.repository.SubjectRepository;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.RegistrationService;
import com.example.mentorsstudents.service.exception.ErrorMessage;
import com.example.mentorsstudents.service.exception.UserAlreadyRegisteredException;
import com.example.mentorsstudents.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    private final UserMapper userMapper;
    private final AboutUserMapper aboutUserMapper;

    private final ImageMapper imageMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserUtil userUtil;

    @Override
    public AfterSuccessRegistrationDto registrationUser(UserRegistrationDto userRegistrationDto) {
        UserRole userRole = checkUserRegistration(userRegistrationDto.getEmail()).getUserRole();
        if (userRole.equals(UserRole.NOT_REGISTERED)) {
            User user = userRepository.save(userRegistrationMapper(userRegistrationDto));
            return userMapper.toDtoAfterRegistrationUer(user, user.getImage(), user.getAboutUser(), user.getSubjects());
        }
        throw new UserAlreadyRegisteredException(ErrorMessage.USER_ALREADY_REGISTERED);
    }

    private User userRegistrationMapper(UserRegistrationDto userRegistrationDto) {
        Image image = imageMapper.toImageFromUserRegistrationDto(userRegistrationDto.getImage());
        AboutUser aboutUser = aboutUserMapper.toAboutUserFromUserRegistrationDto(userRegistrationDto);
        User user = userMapper.toUserFromUserRegistrationDto(userRegistrationDto, image, aboutUser,
                userRegistrationDto.getSubjects());
        passwordEncoder(userRegistrationDto.getPassword(), user);
        return user;
    }

    private void passwordEncoder(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public CheckUserRegistrationDto checkUserRegistration(String email) {
        Optional<User> checkedUser = userRepository.findByLogin(email);
        return checkedUser.isEmpty() ? getNotRegisteredCheckUserDto(email) : getRegisteredCheckUserDto(checkedUser.get());
    }

    private CheckUserRegistrationDto getRegisteredCheckUserDto(User user){
        return CheckUserRegistrationDto.builder()
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .idCustomer(user.getUserId())
                .build();
    }

    private CheckUserRegistrationDto getNotRegisteredCheckUserDto(String email){
        return CheckUserRegistrationDto.builder()
                .email(email)
                .userRole(UserRole.NOT_REGISTERED)
                .build();
    }
}
