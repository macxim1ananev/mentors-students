package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.MentorForSearchPageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MentorService {

    List<MentorForSearchPageDto> findAll(Pageable pageable);
}
