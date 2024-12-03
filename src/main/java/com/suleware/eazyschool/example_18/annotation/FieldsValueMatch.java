package com.suleware.eazyschool.example_18.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.suleware.eazyschool.example_18.validation.FieldsValueMatchValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({
    ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
  String message() default "Fields value does not match";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String field();

  String fieldMatch();

  @Target({
      ElementType.TYPE
  })
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {
    FieldsValueMatch[] value();
  }
}