package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.JwtTokenResponseDto;
import com.example.mentorsstudents.dto.UserAuthRequestDto;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.service.AuthenticationService;
import com.example.mentorsstudents.service.JwtTokenUtil;
import com.example.mentorsstudents.service.UserService;
import com.example.mentorsstudents.service.exception.ErrorMessage;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public JwtTokenResponseDto authenticate(UserAuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getLogin(), authRequestDto.getPassword()));
        final User user = userService.loadUserByLogin(authRequestDto.getLogin());
        final String jwtToken = jwtTokenUtil.generateAccessToken(user);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        return new JwtTokenResponseDto(jwtToken, refreshToken);
    }

    @Override
    public JwtTokenResponseDto refreshAccessToken(String refreshToken) {
        if (jwtTokenUtil.validateRefreshToken(refreshToken)) {
            final User user = userService.loadUserByLogin(
                    jwtTokenUtil.getLoginFromRefreshToken(refreshToken));
            final String newAccessToken = jwtTokenUtil.generateAccessToken(user);
            final String newRefreshToken = jwtTokenUtil.generateRefreshToken(user);
            return new JwtTokenResponseDto(newAccessToken, newRefreshToken);
        }
        throw new JwtException(ErrorMessage.TOKEN_INVALID_MESS);
    }
}
