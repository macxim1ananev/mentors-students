package com.example.mentorsstudents.service.impl;

import com.example.mentorsstudents.dto.JwtTokenResponseDto;
import com.example.mentorsstudents.dto.UserAuthRequestDto;
import com.example.mentorsstudents.entity.User;
import com.example.mentorsstudents.entity.enumiration.UserRole;
import com.example.mentorsstudents.entity.enumiration.UserStatus;
import com.example.mentorsstudents.service.JwtTokenUtil;
import com.example.mentorsstudents.service.UserService;
import com.example.mentorsstudents.util.TestUtil;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @InjectMocks
    private AuthenticationServiceImpl authService;

    @AfterEach
    public void clearMocks() {
        clearInvocations(authenticationManager, jwtTokenUtil, userService);
    }

    @Test
    @DisplayName("Test authentication with correct credential")
    public void correctAuthenticateAndReturnTokens(){
        UserAuthRequestDto authRequestDto = createTestAuthRequest();
        User user = TestUtil.getUser(UserRole.STUDENT, UserStatus.ACTIVE);
        when(userService.loadUserByLogin(authRequestDto.getLogin())).thenReturn(user);

        String accessToken = "access token string";
        String refreshToken = "refresh token string";

        when(jwtTokenUtil.generateAccessToken(user))
                .thenReturn(accessToken);
        when(jwtTokenUtil.generateRefreshToken(user))
                .thenReturn(refreshToken);

        JwtTokenResponseDto tokenResponse = authService.authenticate(authRequestDto);

        assertEquals(new JwtTokenResponseDto(accessToken, refreshToken), tokenResponse);
    }


    @Test
    @DisplayName("Test refreshing access token with correct refresh token")
    public void correctRefreshAccessToken() {
        String refreshToken = "correct refresh token";
        String login = "MM12345";

        when(jwtTokenUtil.validateRefreshToken(refreshToken))
                .thenReturn(true);
        when(jwtTokenUtil.getLoginFromRefreshToken(refreshToken))
                .thenReturn(login);

        User user = TestUtil.getUser(UserRole.MENTOR, UserStatus.ACTIVE);
        when(userService.loadUserByLogin(login))
                .thenReturn(user);

        String newAccessToken = "new access token";
        String newRefreshToken = "new refresh token";

        when(jwtTokenUtil.generateAccessToken(user))
                .thenReturn(newAccessToken);
        when(jwtTokenUtil.generateRefreshToken(user))
                .thenReturn(newRefreshToken);

        JwtTokenResponseDto tokenResponse = authService.refreshAccessToken(refreshToken);

        assertEquals(new JwtTokenResponseDto(newAccessToken, newRefreshToken), tokenResponse);
    }

    @Test
    @DisplayName("Test no refreshing access token when refresh token invalid")
    public void noRefreshAccessTokenWithInvalidRefreshToken() {
        String refreshToken = "invalid token";
        when(jwtTokenUtil.validateRefreshToken(refreshToken))
                .thenReturn(false);

        assertThrows(JwtException.class, () -> authService.refreshAccessToken(refreshToken));
    }

    private UserAuthRequestDto createTestAuthRequest() {
        return new UserAuthRequestDto("+123456789", "test_password");
    }

}