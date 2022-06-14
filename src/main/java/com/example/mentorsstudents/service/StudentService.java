package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.StudentForSearchPageDto;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface StudentService {

    List<StudentForSearchPageDto> findAll(Pageable pageable);
}
