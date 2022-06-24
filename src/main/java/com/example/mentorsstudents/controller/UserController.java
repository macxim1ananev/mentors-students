package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.UserDto;
import com.example.mentorsstudents.dto.UserDtoForUpdate;
import com.example.mentorsstudents.service.UserService;
import com.example.mentorsstudents.service.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User controller", description = "API for user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find user by id.", description = "Find user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User with userId.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "User with userId not find.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserNotFoundException.class))
            })
    })
    public UserDto findUserById(@PathVariable ("id") Long id){
        return userService.findUserById(id);
    }



    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user by id.", description = "Delete user with userId.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User with id has been successfully deleted.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseEntity.class))
            }),
            @ApiResponse(responseCode = "404", description = "User with id not found.", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserNotFoundException.class))
            })
    })
    public ResponseEntity<String> deleteUserById(@PathVariable ("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User with id " + id + " was deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user by id.", description = "Update user with user id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User has been successfully updated.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User with userId not found.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserNotFoundException.class))
            })
    })
    public UserDto updateUserById(@PathVariable ("id") Long id, @RequestBody UserDtoForUpdate user){
        return userService.updateUserById(id, user);
    }
}
