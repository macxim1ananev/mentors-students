package com.example.mentorsstudents.dto;

import com.example.mentorsstudents.entity.enumiration.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CheckUserRegistrationDto {

    String email;
    UserStatus userStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long idCustomer;
}
