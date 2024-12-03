package com.suleware.eazyschool.example_18.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.suleware.eazyschool.example_18.validation.PasswordStrengthValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({
    ElementType.METHOD, ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
  String message() default "Please, choose a stronger password";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}