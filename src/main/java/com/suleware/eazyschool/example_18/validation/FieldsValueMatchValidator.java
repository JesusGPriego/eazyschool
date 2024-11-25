package com.suleware.eazyschool.example_18.validation;

import org.springframework.beans.BeanWrapperImpl;

import com.suleware.eazyschool.example_18.annotation.FieldsValueMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements
    ConstraintValidator<FieldsValueMatch, Object> {

  private String field;
  private String fieldMatch;


  @Override
  public void initialize(
      FieldsValueMatch constraintAnnotation
  ) {
    field = constraintAnnotation.field();
    fieldMatch = constraintAnnotation.fieldMatch();
  }

  @Override
  public boolean isValid(
      Object value,
      ConstraintValidatorContext context
  ) {
    Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
    Object fieldMatchValue =
        new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
    return fieldValue != null && fieldValue.equals(fieldMatchValue);
  }

}
