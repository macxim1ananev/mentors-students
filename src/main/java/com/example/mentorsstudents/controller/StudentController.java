package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.MentorForSearchPageDto;
import com.example.mentorsstudents.dto.StudentForSearchPageDto;
import com.example.mentorsstudents.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentForSearchPageDto>> getAll(@PageableDefault(page = 0, value = 20) Pageable pageable) {
        return ResponseEntity.ok(studentService.findAll(pageable));
    }

}
