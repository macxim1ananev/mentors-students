package com.example.mentorsstudents.service;

import com.example.mentorsstudents.dto.JwtTokenResponseDto;
import com.example.mentorsstudents.dto.UserAuthRequestDto;

public interface AuthenticationService {

    JwtTokenResponseDto authenticate(UserAuthRequestDto authRequestDto);

    JwtTokenResponseDto refreshAccessToken(String refreshToken);
}
