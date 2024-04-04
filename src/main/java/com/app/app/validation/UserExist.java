package com.app.app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserExistValidate.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface UserExist {
    String message() default "el usuario debe ser unic2o";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
