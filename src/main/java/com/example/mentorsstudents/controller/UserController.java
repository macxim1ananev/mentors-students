package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.UserDto;
import com.example.mentorsstudents.dto.UserRegistrationDto;
import com.example.mentorsstudents.service.RegistrationService;
import com.example.mentorsstudents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findUserById(@PathVariable ("id") Long id){
        return userService.findUserById(id);
    }
}
