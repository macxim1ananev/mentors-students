package com.example.mentorsstudents.dto;

import com.example.mentorsstudents.entity.enumiration.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CheckUserRegistrationDto {

    String email;
    UserRole userRole;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long idCustomer;
}
