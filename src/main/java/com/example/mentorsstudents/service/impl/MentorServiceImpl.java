package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.MentorForSearchPageDto;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import com.example.mentorsstudents.mapper.UserMapper;
import com.example.mentorsstudents.repository.UserRepository;
import com.example.mentorsstudents.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<MentorForSearchPageDto> findAll(Pageable pageable) {
        return userRepository.findAllByUserRole(UserRole.MENTOR)
                .stream().map(userMapper::toMentorForSearchPagDtoFromUser)
                .collect(Collectors.toList());
    }


}
