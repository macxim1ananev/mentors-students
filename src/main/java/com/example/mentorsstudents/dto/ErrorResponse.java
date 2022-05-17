package com.example.mentorsstudents.dto;

import lombok.Value;

import java.util.List;

@Value
public class ErrorResponse {

    String message;
    List<ErrorExtension> errorExtensions;
}