package com.example.mentorsstudents.validation.impl;

import com.example.mentorsstudents.validation.annotation.Name;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NameConstraint implements ConstraintValidator<Name, String> {

    private static final String NAME_PATTER = "[\\sa-zA-Zа-яА-Я-]{2,30}";

    @Override
    public void initialize(Name constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean res = false;
        if (!value.isBlank()) {
            res = value.matches(NAME_PATTER);
        }
        return res;
    }
}
