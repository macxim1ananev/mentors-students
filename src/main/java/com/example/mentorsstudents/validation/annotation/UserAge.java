package com.example.mentorsstudents.validation.annotation;

import com.example.mentorsstudents.validation.impl.UserAgeConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserAgeConstraint.class)
public @interface UserAge {
    String message() default "Age will be more than 0 and less than 100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
