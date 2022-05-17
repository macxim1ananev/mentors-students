package com.example.mentorsstudents.validation.impl;

import com.example.mentorsstudents.validation.annotation.UserAge;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserAgeConstraint implements ConstraintValidator<UserAge, Integer> {
    @Override
    public void initialize(UserAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        return age > 0 && age < 100;
    }
}
