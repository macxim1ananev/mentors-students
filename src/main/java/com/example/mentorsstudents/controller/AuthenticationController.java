package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.AccessTokenRequestDto;
import com.example.mentorsstudents.dto.ErrorExtension;
import com.example.mentorsstudents.dto.JwtTokenResponseDto;
import com.example.mentorsstudents.dto.UserAuthRequestDto;
import com.example.mentorsstudents.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication controller", description = "API for authentication users")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login and receive JWT tokens",
            description = "Receive pair of JWT tokens after login via email and password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful authentication", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtTokenResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Invalid login or password", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorExtension.class))
            })
    })
    public JwtTokenResponseDto oneTimeAuthorization(@RequestBody UserAuthRequestDto userAuthRequest) {
        return authenticationService.authenticate(userAuthRequest);
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Refresh JWT tokens with refresh token",
            description = "Receive new pair of JWT tokens by previous refresh token")
    @Parameters({
            @Parameter(in = ParameterIn.HEADER, name = "isRefreshToken", required = true,
                    schema = @Schema(type = "boolean", allowableValues = {"true", "false"}, defaultValue = "true"))
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receive new pair of tokens", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JwtTokenResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorExtension.class))
            })
    })
    public JwtTokenResponseDto refreshJwtToken(@RequestBody AccessTokenRequestDto refreshTokenDto) {
        return authenticationService.refreshAccessToken(refreshTokenDto.getRefreshToken());
    }
}
