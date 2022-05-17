package com.example.mentorsstudents.validation.annotation;

import com.example.mentorsstudents.validation.impl.NameConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NameConstraint.class})
public @interface Name {

    String message() default "Name must no shorter than 2 characters " +
            "not be longer than 30 characters and it can includes at least " +
            "symbols from capital latin letters, lowercase latin letters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
