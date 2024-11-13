package com.suleware.eazyschool.example_18.validation;

import java.util.Arrays;
import java.util.List;

import com.suleware.eazyschool.example_18.annotation.PasswordValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements
    ConstraintValidator<PasswordValidator, String> {

  private List<String> weakPasswords;

  @Override
  public void initialize(
      PasswordValidator constraintAnnotation
  ) {
    this.weakPasswords = Arrays.asList("qwerty", "password", "123456");
  }

  @Override
  public boolean isValid(
      String passwordField,
      ConstraintValidatorContext context
  ) {
    return passwordField != null && (!weakPasswords.contains(passwordField));
  }

}
