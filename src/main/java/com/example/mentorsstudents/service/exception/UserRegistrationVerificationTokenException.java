package com.example.mentorsstudents.service.exception;

public class UserRegistrationVerificationTokenException extends RuntimeException{
    public UserRegistrationVerificationTokenException(String message) {
        super(message);
    }
}
