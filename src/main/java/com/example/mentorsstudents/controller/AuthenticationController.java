package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.AccessTokenRequestDto;
import com.example.mentorsstudents.dto.JwtTokenResponseDto;
import com.example.mentorsstudents.dto.UserAuthRequestDto;
import com.example.mentorsstudents.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<JwtTokenResponseDto> oneTimeAuthorization(@RequestBody UserAuthRequestDto userAuthRequest) {
        final JwtTokenResponseDto responseDto = authenticationService.authenticate(userAuthRequest);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/token")
    public ResponseEntity<JwtTokenResponseDto> refreshJwtToken(@RequestBody AccessTokenRequestDto refreshTokenDto) {
        final JwtTokenResponseDto responseDto = authenticationService.refreshAccessToken(refreshTokenDto.getRefreshToken());
        return ResponseEntity.ok(responseDto);
    }
}
