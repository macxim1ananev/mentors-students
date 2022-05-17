package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.MentorForSearchPageDto;
import com.example.mentorsstudents.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<MentorForSearchPageDto>> getAll(@PageableDefault(page = 0, value = 20) Pageable pageable) {
        return ResponseEntity.ok(mentorService.findAll(pageable));
    }
}
