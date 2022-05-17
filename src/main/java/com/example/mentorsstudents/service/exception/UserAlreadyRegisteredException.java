package com.example.mentorsstudents.service.exception;

public class UserAlreadyRegisteredException extends RegistrationException {

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
