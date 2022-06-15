package com.example.mentorsstudents.service.exception;

public class ErrorMessage {

    // USER
    public static final String USER_ALREADY_REGISTERED =
            "A customer with this email address is already registered, you cannot register again. " +
                    "Sign in to an existing account or enter a new one.";

    public static final String TOKEN_INVALID_MESS = "Token invalid or expired.";

    public static final String AUTH_FAILED_MESS_INCORRECT_PASSWORD = "Incorrect password.";

    //token
    public static final String INVALID_TOKEN_FOR_CONFIRM_REGISTRATION_USER = "Invalid token.";

    public static final String TOKEN_HAS_EXPIRED = "The token has expired to confirm registration, request a new token.";
}