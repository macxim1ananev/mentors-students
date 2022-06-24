package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.MentorForSearchPageDto;
import com.example.mentorsstudents.dto.StudentForSearchPageDto;
import com.example.mentorsstudents.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController()
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "Student controller", description = "API for user with user role student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all students", description = "Find all users with user role student.")
    @ApiResponse(responseCode = "200", description = "All users with user role student", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StudentForSearchPageDto.class))
    })
    public List<StudentForSearchPageDto> getAll(@PageableDefault(page = 0, value = 20) Pageable pageable) {
        return studentService.findAll(pageable);
    }
}
