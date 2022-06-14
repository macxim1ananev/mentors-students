package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.StudentForSearchPageDto;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import com.example.mentorsstudents.mapper.UserMapper;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<StudentForSearchPageDto> findAll(Pageable pageable) {
        return userRepository.findAllByUserRole(UserRole.STUDENT)
                .stream().map(userMapper::toStudentForSearchPageDtoFromUser)
                .collect(Collectors.toList());
    }
}
