package com.example.mentorsstudents.validation.annotation;

import com.example.mentorsstudents.validation.impl.PasswordConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordConstraint.class})
public @interface Password {
    String message() default "Password must be 6-20 characters long and includes at least " +
            "one symbol from capital latin letters, lowercase latin letters, numbers, auxiliary.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

