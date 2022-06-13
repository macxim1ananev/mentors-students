package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.AfterSuccessRegistrationDto;
import com.example.mentorsstudents.dto.UserDto;
import com.example.mentorsstudents.dto.UserDtoForUpdate;
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

    @DeleteMapping("/{id}/")
    public ResponseEntity<String> deleteUserById(@PathVariable ("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User with id " + id + " was deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUserById(@PathVariable ("id") Long id, @RequestBody UserDtoForUpdate user){
        return userService.updateUserById(id, user);
    }
}
