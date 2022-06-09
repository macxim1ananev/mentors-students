package com.example.mentorsstudents.controller;

import com.example.mentorsstudents.dto.ErrorExtension;
import com.example.mentorsstudents.dto.ErrorResponse;
import com.example.mentorsstudents.service.exception.RegistrationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorExtension> errorExtensions = exception.getFieldErrors()
                .stream()
                .map(filedError -> new ErrorExtension(filedError.getDefaultMessage(), filedError.getField()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse("Validation failed", errorExtensions),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorExtension> handleUserRegistrationExceptions(Exception exception) {
        ErrorExtension body = new ErrorExtension(exception.getMessage(),
                String.valueOf(HttpStatus.CONFLICT.value()));
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
