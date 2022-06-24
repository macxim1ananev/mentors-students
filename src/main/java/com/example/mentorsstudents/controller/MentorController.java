package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.MentorForSearchPageDto;
import com.example.mentorsstudents.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/mentors")
@RequiredArgsConstructor
@Tag(name = "Mentor controller", description = "API for user with user role mentor")
public class MentorController {

    private final MentorService mentorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all mentors", description = "Find all users with user role mentor.")
    @ApiResponse(responseCode = "200", description = "All users with user role mentor", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MentorForSearchPageDto.class))
    })
    public List<MentorForSearchPageDto> getAll(@PageableDefault(page = 0, value = 20) Pageable pageable) {
        return mentorService.findAll(pageable);
    }
}
