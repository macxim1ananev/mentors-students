package com.example.mentorsstudents.dto;

import lombok.Value;

@Value
public class JwtTokenResponseDto {

    String jwtToken;
    String refreshToken;
}
